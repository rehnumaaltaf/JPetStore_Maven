<#
	This script will execute build definition created for execting test suites under a vsts test plan.
	It also	create bug under the test suites if any test case fails under the suite.
	The bugs created will be linked to PBIs associatedto the test suites.
#>

<#
     .SYNOPSIS
     Writes message to log file
     .DESCRIPTION
     This function writes LogMessage to specified log file LogFileName
#>
Function Write-Message
{
[CmdletBinding(SupportsShouldProcess=$true)]
Param
(
[Parameter(Mandatory=$true)]
[string] $LogFileName,
[Parameter(Mandatory=$true)]
[string] $LogMessage
)
if($LogFileName)
{
	Try
	{
		$DateTimeStamp = Get-DateTimeStamp
		$Message = "$DateTimeStamp - $LogMessage"
		Write-Host $LogMessage
		Add-content $LogFileName -value $Message
	}
	Catch [Exception]
	{
		#Write-Message $LogFileName "LogFileName in Catch block: $LogFileName"
		$DateTimeStamp = Get-DateTimeStamp
		$ErrorException = $_.Exception.Message
		$DateTimeStamp + " " + $ErrorException | Out-File $LogFileName -Append
		#Exit(1)
	}
}
}
# End : Function #Write-Message



<#
 .SYNOPSIS
 Get Date and Time stamp
 .DESCRIPTION
 Get Date and Time stamp in the format [Month/Date/Year][Hour:Minute:Second]
#>
Function Get-DateTimeStamp
{

	$DateTime = Get-Date
	$Date = $DateTime.ToShortDateString()
	$TimeInHour = $DateTime.Hour
	$TimeInMinutes  = $DateTime.Minute
	$TimeInSeconds = $DateTime.Second
	$DateTimeStamp = "[$Date][$TimeInHour`:$TimeInMinutes`:$TimeInSeconds]"
	return $DateTimeStamp
}
# End : Function Get-DateTimeStamp

<#
 .SYNOPSIS 
 This function is will get environment specific configuration
 .DESCRIPTION
 TO DO:
 .EXAMPLE
   . Get-EnvironmentConfigurations $Environment
#>
Function Get-EnvironmentConfigurations
{


[CmdletBinding(SupportsShouldProcess=$true)]
Param
(
   [Parameter(Mandatory)]
   $LogFileName,
   [Parameter(Mandatory)]
   $EnvironmentPropertiesFile 
)

Try
{
	# Get the path of the script directory
	#$ConfigFileDir = $PSScriptRoot + "\Config.xml"

	Write-Message $LogFileName "EnvironmentPropertiesFile is $EnvironmentPropertiesFile"

	# Verify the properties file is a valid file
	if (!(Test-Path $EnvironmentPropertiesFile))
	{
		Write-Message $LogFileName "$EnvironmentPropertiesFile is not a valid file name or the path is incorrect."
		Exit (1)      
	}
	else
	{
		# Get the contents of the Application properties files
		[xml] $EnvironmentXMLFile           = (Get-Content "$EnvironmentPropertiesFile")
		$EnvironmentPropertiesList          = ($EnvironmentXMLFile.properties)
	}

	$PropertiesFileConfiguration             = @{}
	# Add all Application Specific properties to Properties Hashtable
	foreach ($Property in $EnvironmentPropertiesList.ChildNodes) 
	{
		$PropertiesFileConfiguration.Add($Property.Name,$Property.Innertext)
	}

	# Get the target Application servers
	if ($PropertiesFileConfiguration.vstsAccount)
	{
		$vstsAccount       = $PropertiesFileConfiguration.vstsAccount
		Write-Message $LogFileName "vstsAccount : $vstsAccount"
	}
	
	# Get the vsts user name
	if ($PropertiesFileConfiguration.vstsUser)
	{
		$user       = $PropertiesFileConfiguration.vstsUser
	}
	
	#adding timeout to config
	
	if ($PropertiesFileConfiguration.timeout)
	{
		$timeout       = $PropertiesFileConfiguration.timeout
		Write-Message $LogFileName "timeout : $timeout seconds"
	}
	
	

	# Get the vsts token
	if ($PropertiesFileConfiguration.token)
	{
		$token       = $PropertiesFileConfiguration.token
		
	}

	# Get the SourceBranch
	if ($PropertiesFileConfiguration.SourceBranch)
	{
	$SourceBranch       = $PropertiesFileConfiguration.SourceBranch
	}
	#adding bug priority to properties
	if ($PropertiesFileConfiguration.Priority)
	{
	$Priority       = $PropertiesFileConfiguration.Priority
	}
	
	#adding bug severity to properties
	if ($PropertiesFileConfiguration.Severity)
	{
	$Severity       = $PropertiesFileConfiguration.Severity
	}
	# Get the service_user
	if ($PropertiesFileConfiguration.serviceUserName)
	{
	$service_user       = $PropertiesFileConfiguration.serviceUserName
	}

}
Catch [Exception]
{
	Write-Message $LogFileName  "$_.Exception.Message"
	Exit(1)
}
}
# End : Function Get-EnvironmentConfigurations 


<#
 .SYNOPSIS 
 This function is will edit build definition in vsts to accept testPlan and testSuite variablesm in test task
 .DESCRIPTION
 This function is will edit build definition in vsts to accept testPlan and testSuite variablesm in test task
#>
Function EditBuildDefinition
{
Param(
   [string]$vstsAccount = "",
   [string]$projectName = "",
   [string]$DefinitionId = "",
   [string]$user = "",
   [string]$token = "",
   [string]$LogFileName = ""
)
Try
{
	#Creating Authenticating Token
	$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))
   
	$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"
	
	#Creating Url for Get build definition API Call
	$URI = "$collection"+"/"+$projectName+"/_apis/build/definitions/"+$DefinitionId +"?api-version=2.0"
	
	#Invoking the REST-API to Get build definition		
	#$Response = Invoke-webrequest -Uri $URI -Method GET -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)}
	$Response = Invoke-webrequest -Uri $URI -Method GET -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
	
	#Converting the Json to String Object.
	$ResponseValue = $Response | ConvertFrom-Json
	
	$currentPlanValue=$ResponseValue.build.inputs.testPlan
	$currentPlanValue=$ResponseValue.build.inputs.testSuite
	
	if(!(($currentPlanValue -ceq "`$(testPlan)") -and ($currentPlanValue -ceq "`$(testSuite)")))
	{
		
		Write-Message $LogFileName "[Log] Updating Build definition $DefinitionId of $projectName project"
		#Updating testPlan and testSuite avlues to use build variables $(testPlan) and $(testSuite) respectively

		$ResponseValue.build.inputs | % {if($_.testSelector -eq 'testPlan'){$_.testPlan="`$(testPlan)"}}
		$ResponseValue.build.inputs | % {if($_.testSelector -eq 'testPlan'){$_.testSuite="`$(testSuite)"}}	
		

		# Creating Json Body for updating build definition
		Write-Message $LogFileName "[Log] Creating Json Body for updating build definition"
	   #$Body = $ResponseValue | ConvertTo-Json -Depth 50
	   $Body = $ResponseValue | ConvertTo-Json -Depth 50 -Compress
	   
	   
	   #Creating Url for EditBuildDefinition API Call 
	   Write-Message $LogFileName "[Log] Creating Url for EditBuildDefinition API Call "
	   $URI = "$collection"+"/"+$projectName+"/_apis/build/definitions/"+$DefinitionId+"?api-version=2"
	   
	   #Invoking the REST-API to update build definition in VSTS  
	   Write-Message $LogFileName "[Log] Invoking the REST-API to update build definition in VSTS"
	   $Response = Invoke-webrequest -Uri $URI -Method PUT -ContentType "application/json" -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -Body $Body -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
	   
	}
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "Error while editing Build definition (DefinitionId : $DefinitionId) of $projectName project"
	Write-Message $LogFileName "[Error] : $_.Exception.Message"
	Exit(1)
}
}
#End of EditBuildDefinition



Function QueueBuild
{
<#
 .SYNOPSIS 
 This function is will queuea build in vsts
 .DESCRIPTION
 This function is will queuea build in vsts with the necessary parameters
#>
Param(
   [string]$vstsAccount,
   [string]$projectName,
   [string]$DefinitionId,
   [string]$user,
   [string]$token,
   [string]$SourceBranch,
   [string]$TestRunTitleParam,
   [string]$TestRunTitleValue,
   [string]$TestPlanIdParam,
   [string]$TestPlanIdValue,
   [string]$TestSuiteIdParam,
   [string]$TestSuiteIdValue,
   [string]$LogFileName,
   [string]$IDPWSParamName,
   [string]$IDPWSParamValue,
   [string]$IDPTestParamName,
   [string]$IDPTestParamValue,
   [string]$IDPTestEnvParam,
   [string]$IDPTestEnvValue,
   [string]$IDPTestStepParam,
   [string]$IDPTestStepValue,
   [string]$IDPTestReportParam,
   [string]$IDPTestReportValue,
   [string]$NexusGroupId,
   [string]$NexusArtifactId,
   [string]$NexusVersion
)
Try
{
	$IDPTestParamValue = $IDPTestParamValue -replace '"','\"'
	#Creating Authenticating Token
	Write-Message $LogFileName "[Log]Creating Authenticating Token"
	Write-Message $LogFileName "[Log] SuiteID : $TestSuiteIdValue"
	$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))
   
	$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"

	#Creating Json Body for QueueBuild API Call
	$Body = "{
	  `"definition`"`: {
					`"id`"`: `"$DefinitionId`"`
					},
	   `"sourceBranch`"`: `"$SourceBranch`"`,
	  `"parameters`"`: `" {\`"$TestRunTitleParam\`"`:\`"$TestRunTitleValue\`"`,
						\`"$TestPlanIdParam\`"`:\`"$TestPlanIdValue\`"`,
						\`"$TestSuiteIdParam\`"`:\`"$TestSuiteIdValue\`"`,
						\`"$IDPWSParamName\`"`:\`"$IDPWSParamValue\`"`,
						\`"$IDPTestParamName\`"`:\`'$IDPTestParamValue\`'`,
						\`"$IDPTestEnvParam\`"`:\`"$IDPTestEnvValue\`"`,
						\`"$IDPTestStepParam\`"`:\`"$IDPTestStepValue\`"`,
						\`"$IDPTestReportParam\`"`:\`'$IDPTestReportValue\`'`,
						\`"vstsAccount\`"`:\`"$vstsAccount\`"`,
						\`"user\`"`:\`'$user\`'`,
						\`"token\`"`:\`'$token\`'`,
						\`"groupId\`"`:\`"$NexusGroupId\`"`,
						\`"artifactId\`"`:\`'$NexusArtifactId\`'`,
						\`"version\`"`:\`'$NexusVersion\`'`
						} `"`
	}"
	
	
	
	#Creating Url for QueueBuild API Call
	$URI = "$collection"+"/"+$projectName+"/_apis/build/builds?api-version=2.0"
	#Invoking the REST-API to QueueBuild in VSTS
	Write-Message $LogFileName "[Log]Queuing the Build"
	$Response = Invoke-webrequest -Uri $URI -Method POST -ContentType "application/json" -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -Body $Body -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
	#Converting the Json to String Object.
	$ResponseValue = $Response | ConvertFrom-Json
	#Fetching Build Id for the Queued Build
	$buildId = $ResponseValue.id
	Write-Message $LogFileName "[Log]Queued a Build with BuildID : $buildId"
	Return $buildId
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "Error while Queuing a Build (DefinitionId : $DefinitionId)"
	Write-Message $LogFileName "[Error] : $_.Exception.Message"
	Exit(1)
}
}
#End of QueueBuild


Function GetBuildStatus
{
<#
     .SYNOPSIS
     Get Build Status
     .DESCRIPTION
     This function fetches the build status
#>
Param(
   [string]$vstsAccount,
   [string]$projectName,
   [string]$BuildID,
   [string]$user,
   [string]$token,
   [string]$LogFileName,
   [string]$timeout
)
Try
{
	#Creating Authenticating Token
	$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))
	$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"
	#Creating Url for Getting build Status
	$URI = "$collection"+"/"+$projectName+"/_apis/build/builds/"+$BuildID+"?api-version=2.0"
	Write-Message $LogFileName "[Log]Checking status"
	Write-Message $LogFileName " [completed check] URL $URI"
	#timeout counter
	
	$counter_timeout=0
	Do {
	#Setting polling interval to check Build Status
	Start-Sleep -s 10
	#incrementing counter to check timeout
	$counter_timeout+=10
	#time out when counter exceeds 1800 secs(30 mins) 
    if($counter_timeout -ge $timeout)
    {
	Write-Message $LogFileName "[Log] Timed out....."
	Write-Message $LogFileName "[Log] Exiting......."
	exit(1)
	}
	#Invoking URI to get the Build Status
	
	$Response = Invoke-webrequest -Uri $URI -Method GET -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
	#Converting the Json to String Object.
	$ResponseValue = $Response | ConvertFrom-Json
	
	Write-Message $LogFileName " ResponseValue $ResponseValu"
	
	#Fetching the status of the Build
	$BuildStatus = $ResponseValue.status
	Write-Message $LogFileName " Buildstatus $BuildStatus"
	
	Write-Message $LogFileName "[Log]$BuildStatus..."
		}
	Until ($BuildStatus -eq "Completed")
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "Error while Getting Build Status (Build ID : $BuildID)"
	Write-Message $LogFileName "[Error] : $_.Exception.Message"
	Exit(1)
}
}
#End of GetBuildStatus


Function FetchTestRunID
{
<#
     .SYNOPSIS
     Fetch Test RunID
     .DESCRIPTION
     This function fetches the test RunID
#>
Param(
   [string]$vstsAccount,
   [string]$projectName,
   [string]$user,
   [string]$token,
   [string]$BuildNo,
   [string]$LogFileName
  
)
Try
{
	#Creating Authenticating Token
	$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))

	$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"

	#Creating URI to Query Build Log using REST-API
	$URI = "$collection"+"/"+$projectName+"/_apis/build/builds/"+$BuildNo+"/logs?api-version=2.0"
	#Calling the REST-API to Query Build Log
	Write-Message $LogFileName "[Log]Fetching Build logs of Build (Build ID : $BuildNo)"
	$response = Invoke-webrequest -Uri $URI -Method GET  -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
    
    Write-Host "******* Build Logs Response : $response"
    
	#Converting the Json to String Object.
	$ResponseValue = $response.content | ConvertFrom-Json
	#Fetching the Url Count from the REST-API Response
	$Count = $ResponseValue.count
	Write-Host "******* Build Logs Count : $Count"
	#Fetching the URL Fields from the Build Log
	$url = $ResponseValue.value.url.split(" ")
	#parsing each build log to get the runId
	for($urlcount=1;$urlcount -le $Count;$urlcount++)
    {
    $BuildUrl = $url[$Count-$urlcount]
    
    #Invoking URI to get the  Build Log of each task starting from last
    $response = Invoke-webrequest -Uri $BuildUrl -Method GET  -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
    #Fetching the content of the REST-API Response
    $responseContent=$response.content
    Write-Host "******* Individual Build Logs Response : $responseContent"
    #regular expression to match 'runId=number'
    [regex]$Regex = "runId=.([0-9]*)"
    $Matches =$Regex.Matches($responseContent)
    if ($Matches -ne $null)
    {
    $runIdtxt=$Matches.Value
    $runidFull=$runIdtxt -split '='
    $RunId=$runidFull[-1]
	Write-Message $LogFileName "[Log]Test Run ID : $RunId"
	Return $RunId
    }
    }
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "Error while Fetching TestRunID (Build ID : $BuildID)"
    Write-Message $LogFileName "[Error] : $_.Exception.Message"
    Exit(1)
}
}
#End of FetchTestRunID



Function getTestRunStatus
{
<#
     .SYNOPSIS
     Function to get test run status
     .DESCRIPTION
     This function fetches the test run status
#>
Param(
   [string]$vstsAccount,
   [string]$projectName,
   [string]$TestRunID,
   [string]$user,
   [string]$token,
   [string]$LogFileName
  
)
Try
{
	#Creating Authenticating Token
	$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))

	$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"
	#Creating URI to Query the Test Run Result
	$URI = "$collection"+"/"+$projectName+"/_apis/test/runs/"+$TestRunID+"?api-version=1.0"

	#Calling the REST-API to Query the Test Run Result
	Write-Message $LogFileName "[Log]Fetching Results of Test Run : $TestRunID"
	$response = Invoke-webrequest -Uri $URI -Method GET  -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
	#Converting the Json to String Object.
	$ResponseValue = $response | ConvertFrom-Json
	#Fetching the passedTests Field from the TestRunResult
	$PassedTestNumber = $ResponseValue.passedTests
	#Fetching the totalTests Field from the TestRunResult
	$TotaltestNumber = $ResponseValue.totalTests
	#Checking whether Test Run is Success
	Write-Message $LogFileName "[Log]Checking status..."
	if($TotaltestNumber -ne $PassedTestNumber)
	{
		Write-Message $LogFileName "[Log]Test Run (TestRunID : $TestRunID) Failed..."
		Return $False
	}
	else
	{
		Write-Message $LogFileName "[Log]Test Run (TestRunID : $TestRunID) Success..."
		Return $True
	}
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "Error while Getting TestRunStatus (TestRunID : $TestRunID)"
                Write-Message $LogFileName "[Error] : $_.Exception.Message"
                Exit(1)
}
}
#End of getTestRunStatus



Function CreateBugUnderWorkItem
{
<#
     .SYNOPSIS
     Function to create bug
     .DESCRIPTION
     This function creates bug under the work item
#>
Param(
   [string]$vstsAccount,
   [string]$projectName,
   [string]$user,
   [string]$token,
   [string]$BugTitle,
   [string]$WorkItemId,
   [string]$LogFileName,
   [string]$failedTestIds,
   [string]$environment,
   [string]$idpBuildNo,
   [string]$RunId,
   [string]$IDPReleaseNo,
   [string]$Priority,
   [string]$Severity,
   [string]$AreaPath,
   [string]$IterationPath
  
)
Try
{
	
	
	Write-Message $LogFileName "Creating Bug in https://$vstsAccount.visualstudio.com under WorkItem : $WorkItemId"
	$failedtest_array=$failedTestIds -split ","
	$failedCaseDetails="<html><b><i><u>Failed Test Cases</u></i></b></br>"
	foreach($failedcase in $failedtest_array)
	{
		$failedCaseDetails= $failedCaseDetails + $failedcase +"</br>"
	}
	$failedCaseDetails = $failedCaseDetails + "</html>"
	$failedtestcount = $failedtest_array.count
	#Creating Authenticating Token
	$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))
	$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"
	#Creating Json Body for CreateVSTSBug API Call
	
	# Replace all \ with \\ in AreaPath and IterationPath fields
	$AreaPath = $AreaPath.Replace("\", "\\")
	$IterationPath = $IterationPath.Replace("\", "\\")
	Write-Message $LogFileName "$AreaPath and $IterationPath"
	$IDPInfoDetails="<html>IDP Build: "+$idpBuildNo+"</br> Test Run :<a href='https://$vstsAccount.visualstudio.com/$projectName/_testManagement/runs?_a=runCharts&runId=$RunId'>"+$RunId+"</a></html>"
	
$Body ="
[     
	{
	  `"op`"` : `"add`"`,
	  `"path`"` : `"/fields/System.Title`"`,
	  `"value`"` : `"$BugTitle`"`
	},
	{
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/SparkProcess.FailedTestcases`"`,
		`"value`"` : `"$failedCaseDetails`"`
	},
	{
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/SparkProcess.Environment`"`,
		`"value`"` : `"$environment`"`
	},
	{
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/SparkProcess.DefectStatus`"`,
		`"value`"` : `"New`"`
	},
	{
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/SparkProcess.BuildNumber`"`,
		`"value`"` : `"$IDPReleaseNo`"`
	},
	{
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/Microsoft.VSTS.Common.Priority`"`,
		`"value`"` : `"$Priority`"`
	},
	{
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/Microsoft.VSTS.Common.Severity`"`,
		`"value`"` : `"$Severity`"`
	},
	{
	
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/SparkProcess.IDPInfo`"`,
		`"value`"` : `"$IDPInfoDetails`"`
	},
	{
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/SparkProcess.Idealdefectdetectionphase`"`,
		`"value`"` : `"System Test`"`
	},
	{
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/SparkProcess.Numberoftestcasesimpacted`"`,
		`"value`"` : `"$failedtestcount`"`
	},
	{
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/System.AreaPath`"`,
		`"value`"` : `"$AreaPath`"`
	},
	{
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/System.IterationPath`"`,
		`"value`"` : `"$IterationPath`"`
	},
	{
	  `"op`"` : `"add`"`,
	  `"path`"` : `"/relations/-`"`,
	  `"value`"` : {
			 `"rel`"` : `"System.LinkTypes.Hierarchy-Reverse`"`,
			 `"url`"` : `"$collection/_apis/wit/workItems/$WorkItemId`"`
	  }
	}
]"
	#Creating Url for CreateVSTSBug API Call
	$URI = "$collection"+"/"+$projectName+"/_apis/wit/workitems/$"+"bug?api-version=1.0"
	#Invoking the REST-API to CreateVSTSBug
	
	$Response = Invoke-webrequest  -Uri $URI -Method PATCH -ContentType "application/json-patch+json" -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -Body $Body -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
	#Converting the Json to String Object.
	$ResponseContent = $Response.content | ConvertFrom-Json
	#Fetching BugID from REST-API Response Content
	$BugID = $ResponseContent.id
	Write-Message $LogFileName "[Log]Successfully Created Bug (ID : $BugID) under Work Item : $WorkItemId"
	return $BugID
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "Error while Creating a Bug in VSTS for Work Item : $WorkItemId)"
	Write-Message $LogFileName "[Error] : $_.Exception.Message"
	Exit(1)
}
}
#End of CreateBugUnderWorkItem
 


Function Get-ObjectMembers
{
<#
     .SYNOPSIS
     This Function would parse a JSON object and Return the value of the Key Being passed.
     .DESCRIPTION
     This Function would parse a JSON object and Return the value of the Key Being passed.
#>
[CmdletBinding(SupportsShouldProcess=$true)]
Param
(
[Parameter(Mandatory=$True)]
[PSCustomObject]$jsonObj,
[Parameter(Mandatory = $True)]
[string]$keyValue
)
try
{
	$json = $jsonObj | ConvertFrom-Json
	[string[]]$Keys = $($json| Get-Member -MemberType *Property).Name

	foreach ($key in $Keys)
	{
	if($key -eq $keyValue)
	{
	#Write-Host "[LOG] The Key Value is : $json.$key"
	return $json.$key
	}
	else
	{
	$subKeys = $json.$Key
	$subKeyPair=$($subKeys| Get-Member -MemberType *Property).value
	$subKeyPair | ForEach-Object {
	$object = $subKeys | Select-Object -Expand $_
	$return = $object.$keyValue}
	if(![string]::IsNullOrEmpty($return))
	{
	#Write-Host "[LOG] The Key Value is : $return"
	return $return
	}
	}
	}
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "[Error] : $_.Exception.Message"
    Exit(1)
}
}
 

Function Create-Map
{
<#
     .SYNOPSIS
     This Function would create and return a hash table of test cases and test suites.
     .DESCRIPTION
     This Function would create and return a hash table of test cases and test suites.
#>
[CmdletBinding(SupportsShouldProcess=$true)]
Param(
   [string]$vstsAccount,
   [string]$projectName,
   [string]$user,
   [string]$token,
   [string]$planId,
   [string]$suiteIdList
  
)
 
try
{
$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))
$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"
 
#getting suite ids in a list
$suite=$suiteIdList -split ","
$testId_array=@()
$hashtable=@{}
foreach ($suiteId in $suite)
{

	$URI = "$collection"+"/"+$projectName+"/_apis/test/plans/$planId/suites/$suiteId/testcases?api-version=2"

	#Calling the REST-API to Get the TestCase Ids within the Suite
	$response=Invoke-WebRequest  -Uri $URI -Method GET  -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
	$JsonContent=$response.Content

	$ResponseValue = $response | ConvertFrom-Json
	if($ResponseValue.count -ne '0')
	{
		#calling JSON Parser method to get the test ids
		$testCaseValue=Get-ObjectMembers $JsonContent "testCase" | ConvertTo-Json
		$testCase=Get-ObjectMembers $testCaseValue "id"
		$testCase=$testCase.split(" ")
		#$testCase.count

		#parse each testcase and store it in
		foreach($test in $testCase)
		{                                                         
	  
		$key = $test
		if($hashtable.ContainsKey($test))
		{
		$existingValue = $hashtable[$key]
		$value = $existingValue + "," + $suiteId
		$hashtable[$key]=$value                   
		
		}
		else
		{
		$value = $suiteId
		$hashtable.Add($key,$value)
		}
		} 
	}		
}

	Return $hashtable
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "[Error] : $_.Exception.Message"
    Exit(1)
} 
}

Function getFailedSuites
{
<#
     .SYNOPSIS
     This Function would fetch the failed suites
     .DESCRIPTION
     This Function would fetch the failed suites
#>
[CmdletBinding(SupportsShouldProcess=$true)]
Param(
   [string]$vstsAccount,
   [string]$projectName,
   [string]$user,
   [string]$token,
   [string]$runId, 
   [string]$planId,#plan id from IDP
   [string]$suiteIdList#comma seperated list of suite ids from IDP
)
try
 {
	# Calling function to get testcase-testsuite mapping
	$caseSuiteMapping = Create-Map -vstsAccount $vstsAccount -projectName $projectName -user $user -token $token -planId $planId -suiteIdList $suiteIdList
	 
    Write-Message $LogFileName "$user .... $token"
	$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"
	$URI = "$collection"+"/"+$projectName+"/_apis/test/runs/"+$runId+"/results?api-version=3.0"
	Write-Message $LogFileName "$URI"
	$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))
	#Calling the REST-API to Get the TestCase Ids within the Suite
	$response=Invoke-WebRequest  -Uri $URI -Method GET  -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
	Write-Message $LogFileName "reached response .....$response"
	$JsonContent = $Response | ConvertFrom-Json
	Write-Message $LogFileName "reached Json...$JsonContent"
	 
	$totalCases = $JsonContent.count
	Write-Message $LogFileName "reached totalCases...$totalCases"

	#$testSuites_array=@()
	$failedSuites=@{}
	Write-Message $LogFileName "reached failedSuites...$failedSuite"
	
	for($case=0; $case -le $totalCases; $case++)
	{
		Write-Message $LogFileName "inside for"
		
		if($JsonContent.value[$case].outcome -eq "Failed")
		{
		$failedCase=$JsonContent.value[$case].testCase.id
		$failedCaseName=$JsonContent.value[$case].testCase.name							
		
		$suiteList=$caseSuiteMapping[$failedCase]
		
		$suite=$suiteList -split ","                            
		foreach ($suiteId in $suite)
		{
		    Write-Message $LogFileName "inside foreach"
		if($failedSuites.ContainsKey($suiteId))
		{
		$failedCases=$failedSuites.Get_Item($suiteId)
		if(!$failedCases.contains($failedCase))
		{
		$failedCasesUpdated = $failedCases + ",<b>" + $failedCase + ":</b>" + $failedCaseName
		$failedSuites.Set_Item($suiteId, $failedCasesUpdated)
		}
		}
		else
		{
		    Write-Message $LogFileName "inside else"
		$failedSuites.Add($suiteId, "<b>"+$failedCase + ":</b>" + $failedCaseName)
		}
		}			
						 
		}
	}
    Write-Message $LogFileName " returning failedsuites "
	return $failedSuites
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName " inside catch failed suites"
    Write-Message $LogFileName "[Error] : $_.Exception.Message"
    Exit(1)
} 
}

Function UpdateBugLinkWithWorkItem
{
<#
     .SYNOPSIS
     This Function would Update Bug Linked With WorkItem
     .DESCRIPTION
     This Function would Update Bug Linked With WorkItem
#>
Param(
   [string]$vstsAccount = "",
   [string]$projectName = "",
   [string]$user = "",
   [string]$token = "",
   [string]$BugId = "",
   [string]$PBI = ""
)

try
{
$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"
$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))
$Body ="
[              
    {
        `"op`"` : `"add`"`,
        `"path`"` : `"/relations/-`"`,
        `"value`"` : {
                        `"rel`"` : `"System.LinkTypes.Related`"`,
                        `"url`"` : `"$collection/_apis/wit/workItems/$BugId`"`,
                        `"attributes`"`:{
                        `"comment`"`:`"Associated PBI`"`
                        }
        }
    }
]"
           
#URL for the rest api call
$URI = $collection+"/_apis/wit/workitems/"+$PBI+"?api-version=1.00"
                
#rest API call to add bug as link in "Related" items in the PBI
$Response = Invoke-webrequest  -Uri $URI -Method PATCH -ContentType "application/json-patch+json" -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -Body $Body -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
	

}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "[Error] : $_.Exception.Message"
    Exit(1)
}
}



Function getWorkItemType
{
<#
     .SYNOPSIS
     This Function would get the WorkItem type
     .DESCRIPTION
     This Function would get the WorkItem type
#>
Param(
   [string]$vstsAccount,
   [string]$projectName,
   [string]$WorkItemId, 
   [string]$user,
   [string]$token
   
)
try
{
	$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))
	 
	$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"

	$URI = "$collection/_apis/wit/workitems/"+$WorkItemId+"?api-version=1.0"
	#Calling the REST-API to Run the Test
	$response = Invoke-Webrequest -Uri $URI -Method GET  -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
	
	$ResponseContent = $response.content | ConvertFrom-Json
	$Fields = $ResponseContent.fields
	$AreaPath = $Fields."System.AreaPath"
	$IterationPath = $Fields."System.IterationPath"
	$WorkItemType = $Fields."System.WorkItemType"
	$WorkItemDetails = $WorkItemType + ";"+ $AreaPath +";" + $IterationPath
	Return $WorkItemDetails
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "[Error] : $_.Exception.Message"
    Exit(1)
}
}

Function getSuiteRelations
{
<#
     .SYNOPSIS
     This Function would get the Suite Relations
     .DESCRIPTION
     This Function would get the Suite Relations
#>
Param(
   [string]$vstsAccount,
   [string]$projectName,
   [string]$SuiteID, 
   [string]$user,
   [string]$token
   
)
try
{
	Write-Message $LogFileName " inside try of getSuiteRelations "
	$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))
	
	Write-Message $LogFileName "passed base64AuthInfo"
	$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"

	$URI = "$collection"+"/_apis/wit/workitems/"+$SuiteID+"?$"+"expand=relations&api-version=1.0"
	
	Write-Message $LogFileName "passed uri $URI"
	#Calling the REST-API to Run the Test
	$response = Invoke-Webrequest -Uri $URI -Method GET  -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
	$ResponseValue = $response.content | ConvertFrom-Json
	Write-Message $LogFileName "passed RESPONSEVALUE"
	$relations = $ResponseValue.relations 
	$relationUrls =$relations.url.split(" ") 
	$LinkedWorkItems = @()
	foreach ($relationUrl in $relationUrls)
	{
	    Write-Message $LogFileName "INSIDE FOR EACH AFTER LINKEDWORKITEMS"
		$relationUrl = $relationUrl.split("/")
		$LinkedWorkItem = $relationUrl[-1]
		$LinkedWorkItems += $LinkedWorkItem
	}
	
	Write-Message $LogFileName "passed FOR EACH"
	return $LinkedWorkItems
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "[Error] : $_.Exception.Message"
    Exit(1)
}
}

Function CopyReportsToAgent
{
<#
     .SYNOPSIS
     This Function would copy reports to agent path
     .DESCRIPTION
     This Function would copy reports to agent path
#>
Param(
   [string]$vstsAccount,
   [string]$projectName,
   [string]$user,
   [string]$token,
   [string]$BuildNo,
   [string]$service_user,
   [string]$zippath=$Env:IDP_WS+"\Reports\"+$Env:ENVIRONMENT+"_"+$Env:STEP_NAME,
   [string]$IDPTestReportValue
   
   )
try
{  
		#Creating Authenticating Token
		$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))

		$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"

		#Creating URI to Query Build Log using REST-API
		$URI = "$collection"+"/"+$projectName+"/_apis/build/builds/"+$BuildNo+"/logs?api-version=2.0"
		
		Write-Message $LogFileName "$user .... $token  and URI $URI"
		#Calling the REST-API to Query Build Log
		$response = Invoke-webrequest -Uri $URI -Method GET  -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
		Write-Message $LogFileName "reached response....$response..."
		#Converting the Json to String Object.
		$ResponseValue = $response.content | ConvertFrom-Json
		Write-Message $LogFileName "reached ResponseValue....$ResponseValue..."
		#Fetching the Url Count from the REST-API Response
		$Count = $ResponseValue.count
		Write-Message $LogFileName "Count $Count"
		#Fetching the URL Fields from the Build Log
		$url = $ResponseValue.value.url.split(" ")
		Write-Message $LogFileName "reached url....$url..."
		#parsing each build log to get the agent name and directory
		for($urlcount=$Count;$urlcount -ne "0";$urlcount--)
		{
			Write-Message $LogFileName "Inside for"
			$BuildUrl = $url[$Count-$urlcount]
			Write-Message $LogFileName "BuildUrl $BuildUrl"
			$response = Invoke-webrequest -Uri $BuildUrl -Method GET  -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
			Write-Message $LogFileName "response $response "
			#Fetching the content of the REST-API Response
			$responseContent=$response.content
			Write-Message $LogFileName "responseContent $responseContent "
			
			#regular expression to match 'Agent_MachineName: and Agent_RootDirectory: '
			[regex]$Regex1 = "Agent_MachineName:.([^\s]+)"
			[regex]$Regex2 = "Build_SourcesDirectory:.([^\s]+)"
			[regex]$Regex3 = "Shared_Path_UName:.([^\s]+)"
			#getting matches from build log
			$Matches1 =$Regex1.Matches($responseContent)
			$Matches2 =$Regex2.Matches($responseContent)
			$Matches3 =$Regex3.Matches($responseContent)
			
			if ($Matches1 -ne $null)
			{
    			$runIdtxt=$Matches1.Value
    			$runidFull=$runIdtxt -split ':',2
    			$machine_name=$runidFull[-1]
			}
			if ($Matches2 -ne $null)
			{
    			$runIdtxt=$Matches2.Value
    			$runidFull=$runIdtxt -split ':',2
    			#$runidFull=$string.split([string[]]"$runIdtxt,",[StringSplitOptions]"None")[1]
    			$machine_directory=$runidFull[-1]
			}
			if ($Matches3 -ne $null)
			{
    			$runIdtxt=$Matches3.Value
    			$runidFull=$runIdtxt -split ':',2
    			$machine_sharedPath=$runidFull[-1]
    			break
			}			
		}
		Write-Message $LogFileName "[LOG] Agent Machine Name : $machine_name, VSTS Build Workspace : $machine_directory, Shared Path Name : $machine_sharedPath"
		
		#To copy files from agent to remote machine
		#$sourcepath=$machine_directory+"\"+$IDPTestReportValue
		#$server = $machine_name 
		#$user = $service_user
		#$SrvPath = $sourcepath

		#Write-Message $LogFileName " passed Invoke-Command"

		#$agentpath="\\"+$machine_name+"\"+"$machine_sharedPath\*"
		<#
		if (Test-path $agentpath)
		{
		    Copy-Item $agentpath -Destination $zippath -recurse -force -Container
		    write-host "Reports copied to $zippath"
	    	$agentname="\\"+$machine_name
           #net share reports-share $agentname /delete /Y
           Start-Sleep -s 50
           net share $machine_sharedPath $agentname /delete /Y
		}
		else
		{
		    Write-Host "[ERROR] $agentpath not accessible. Reports not copied to build server."
		}
		#write-host "reports copied from $sourcepath"
		
		#>
		Write-Message $LogFileName "last of CopyReportsToAgent"
		
}
Catch
{
    $ErrorMessage = $_.Exception.Message
	Write-Message $LogFileName " catch of CopyReportsToAgent"
    Write-Message $LogFileName "[Error] : $_.Exception.Message"
    Exit(1)
}
}


Function ZipResultFiles
{
<#
     .SYNOPSIS
     This Function would Zip the Result Files
     .DESCRIPTION
     This Function would Zip the Result Files
#>
param(
	[string]$source=$Env:IDP_WS+"\Reports\"+$Env:ENVIRONMENT+"_"+$Env:STEP_NAME,
	[string]$destination=$Env:IDP_WS+"\Reports\"+"TestResults_"+$Env:ENVIRONMENT+"_"+$Env:STEP_NAME+".zip"
	
	)
try
{
	Write-Message $LogFileName " source $source....destination$destination"
	If(Test-path $destination) {Remove-item $destination}
	Add-Type -assembly "system.io.compression.filesystem"
	[io.compression.zipfile]::CreateFromDirectory($Source, $destination)

}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "[Error] : $_.Exception.Message"
    Exit(1)
}
}
Function AttachResultsToRun
{
<#
     .SYNOPSIS
     This Function would Attach Results To Run
     .DESCRIPTION
     This Function would Attach Results To Run
#>
param(
	[string]$user,
	[string]$token,
	[string]$vstsAccount,
	[string]$projectName,
	[string]$runId,
	[string]$fileName = $Env:IDP_WS+"\Reports\"+"TestResults_"+$Env:ENVIRONMENT+"_"+$Env:STEP_NAME+".zip",
	[string]$LogFileName
)

try
{
	Write-Message $LogFileName "[Log] Attaching File $fileName  to run id $runId"
	 
	$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))
   
	$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"   
	
	#Convert file to base64
	
	$fileContentEncoded = [Convert]::ToBase64String([IO.File]::ReadAllBytes($fileName))
 
	#Creating Json Body for Attach file API Call
	$Body = "{     
      `"stream`"`: `"$fileContentEncoded`"`,
	  `"fileName`"`: `"Results.zip`"`,
	  `"comment`"`: `"Test Result attachment upload`"`,  
	  `"attachmentType`"`: `"GeneralAttachment`"`	  
   }"
   
 
   #Creating Url for QueueBuild API Call
   $URI = "$collection"+"/"+$projectName+"/_apis/test/runs/"+$runId+"/attachments?api-version=2.0-preview"
   #Invoking the REST-API to QueueBuild in VSTS
   Write-Message $LogFileName "[Log]Attaching the result file"
   $Response = Invoke-webrequest -Uri $URI -Method POST -ContentType "application/json" -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -Body $Body -ProxyUseDefaultCredentials -Proxy 'http://10.68.248.102:80'
   #Converting the Json to String Object.
   $ResponseValue = $Response | ConvertFrom-Json
   #Fetching Build Id for the Queued Build
   $buildId = $ResponseValue.id
   Write-host "[Log]Successfully attached the test result file to test Run: $runId "
   
   
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "[Error] : $_.Exception.Message"
    Exit(1)
}
}  


Function IntegrateIDP_TestHub
{
<#
     .SYNOPSIS
     This is the main function which calls rest of the functions.
     .DESCRIPTION
     <#
	This script will execute build definition created for execting test suites under a vsts test plan.
	It also	create bug under the test suites if any test case fails under the suite.
	The bugs created will be linked to PBIs associatedto the test suites.
#>
#>
Param(
        
	   [string]$projectName=$Env:MTM_PROJECT_NAME,
	   #Vsts build Parameter name linked to test run title
       [string]$TestRunTitleParam = "TestRunTitle",
	   # Test run title to create the vsts test run
       [string]$TestRunTitleValue = $Env:APPLICATION_NAME+"_"+$Env:ENVIRONMENT+"_"+$Env:BUILD_NO,
       [string]$TestPlanIdParam = "testPlan",
	   #Test plan id to be obtained from IDP	   
       [string]$TestPlanIdValue = $Env:MTM_TEST_PLAN_ID,
	   [string]$TestSuiteIdParam = "testSuite",
	   # Test suit ids to be obtained from IDP
	   [string]$TestSuiteIdValue = $Env:MTM_TESTSUITE_ID,
	   # build definition id (From idp or properties file)
	   [string]$DefinitionId = $Env:MTM_BUILD_DEF_ID,
       [string]$BugTitle = 'AutomatedBugCreationTest'+$Env:BUILD_NO,
	    #Additional Parameter 
	   [string]$IDPWSParamName="IDP_WORKSPACE",
	   [string]$IDPWSParamValue=$Env:IDP_WS,
	   [string]$IDProjectValue=$Env:IDP_PROJECT_NAME,
	   [string]$IDPTestParamName="IDP_TEST_PARAM",
	   [string]$IDPTestParamValue=$Env:TEST_PARAM,
	   [string]$IDPTestEnvParam="IDP_TEST_ENV",
	   [string]$IDPTestEnvValue=$Env:ENVIRONMENT,
	   [string]$IDPTestStepParam="IDP_TEST_STEP_NAME",
	   [string]$IDPTestStepValue=$Env:STEP_NAME,
	   [string]$IDPTestReportParam="IDP_TEST_REPORT_PATH",
	   [string]$IDPTestReportValue=$Env:REPORT_PATH,
	   [string]$IDPReleaseNo=$Env:RELEASENUMBER, #IDP parameter to get the release number
	   [string]$NexusGroupId="demotg.4_5_0", #$Env:, #IDP parameter to get the nexus groupId
	   [string]$NexusArtifactId="demotg_cataloguejun30", #$Env:, #IDP parameter to get the nexus artifactId
	   [string]$NexusVersion="2" #$Env: #IDP parameter to get the nexus version
)
Try
{             
	#Creating Output Log File Path
	$LogFileName = $IDPWSParamValue + "\" + "Execute_Test_$(Get-Date -format "MMddyy-HHmmss").log"
	Write-Message $LogFileName "------------------------------------------------------------------"
	Write-Message $LogFileName "          Starting Test Execution in Test Hub from IDP for $TestSuiteIdValue and $TestPlanIdValue  "
	Write-Message $LogFileName "------------------------------------------------------------------"
	#Fetching Environment Properties
	. Get-EnvironmentConfigurations -LogFileName $LogFileName -EnvironmentPropertiesFile "$IDPWSParamValue\$IDProjectValue\Properties\Properties.xml"
	
	# Editing build definition to accept testSuite and testPlan variables
	Write-Message $LogFileName "Editing build definition (DefenitionId : $DefinitionId) of Project '$projectName' in https://$vstsAccount.visualstudio.com to accept testSuite and testPlan variables"
	EditBuildDefinition -vstsAccount $vstsAccount -projectName $projectName -DefinitionId $DefinitionId -user $user -token $token -LogFileName $LogFileName
	
	
	#Queuing a VSTS Build containing Test task
	Write-Message $LogFileName "Queuing a Build with Test Execution for Build (DefenitionId : $DefinitionId) of Project '$projectName' in https://$vstsAccount.visualstudio.com"
	$BuildID = QueueBuild -vstsAccount $vstsAccount -projectName $projectName -DefinitionId $DefinitionId -user $user -token $token -SourceBranch $SourceBranch -TestRunTitleParam $TestRunTitleParam -TestRunTitleValue $TestRunTitleValue -TestPlanIdParam $TestPlanIdParam -TestPlanIdValue $TestPlanIdValue -TestSuiteIdParam $TestSuiteIdParam -TestSuiteIdValue $TestSuiteIdValue -LogFileName $LogFileName -IDPWSParamName $IDPWSParamName -IDPWSParamValue $IDPWSParamValue -IDPTestParamName $IDPTestParamName -IDPTestParamValue $IDPTestParamValue -IDPTestEnvParam $IDPTestEnvParam -IDPTestEnvValue $IDPTestEnvValue -IDPTestStepParam $IDPTestStepParam -IDPTestStepValue $IDPTestStepValue -IDPTestReportParam $IDPTestReportParam -IDPTestReportValue $IDPTestReportValue -NexusGroupId $NexusGroupId -NexusArtifactId $NexusArtifactId -NexusVersion $NexusVersion
	
	Write-Message $LogFileName "Checking Status of Build (BuildID : $BuildID) of Project '$projectName' in https://$vstsAccount.visualstudio.com"
	#Checking the status of the Executed Build
	$BuildStatus = GetBuildStatus -vstsAccount $vstsAccount -projectName $projectName -BuildID $BuildID -user $user -token $token -LogFileName $LogFileName -timeout $timeout
	Write-Message $LogFileName "Fetching Test Run Id  of Test Run Executed in the Build (BuildID : $BuildID)"
	
	#Fetching TestRunID of TestRun Executed by Build
	$TestRunID = FetchTestRunID -vstsAccount $vstsAccount -projectName $projectName -user $user -token $token -BuildNo $BuildID -LogFileName $LogFileName
	#Fetching the Status of the Test Run
	if($TestRunID -eq $null)
	{
		Write-Message $LogFileName "[ERROR] : Unable to fetch runId. Please check build $BuildID and ensure test task is run"
		Exit(1)
	}
	
	#[string[]]$SuiteIDs = @{}
	#Getting the failed suites
	$SuiteIDs = getFailedSuites -vstsAccount $vstsAccount -projectName $projectName -user $user -token $token -runId $TestRunID -planId $TestPlanIdValue -suiteIdList $TestSuiteIdValue
	#AttachResultsToRun -user $user -token $token -vstsAccount $vstsAccount -projectName $projectName -runId $TestRunID -fileName $fileName
	
	
	
	
	#Copying reports from agent machine to idp slave 
	CopyReportsToAgent -vstsAccount $vstsAccount -projectName $projectName -user $user -token $token -BuildNo $BuildID -service_user $service_user -IDPTestReportValue $IDPTestReportValue 
	Write-Message $LogFileName " copy reports passed "
	$SuiteIDKeys = $SuiteIDs.keys
	Write-Message $LogFileName " suiteidkeys done "
	$count=$SuiteIDKeys.count
	Write-Message $LogFileName " count done "
	if($count -eq 0)  
	{	
		Write-Message $LogFileName " inside if before ZipResultFiles "
		ZipResultFiles
		AttachResultsToRun -user $user -token $token -vstsAccount $vstsAccount -projectName $projectName -runId $TestRunID -LogFileName $LogFileName
		Write-Message $LogFileName "[Log]Test Execution completed successfully"
		Exit(0)
	}
	else
	{
	foreach($SuiteID in $SuiteIDKeys)
		{
			Write-Message $LogFileName " Inside for-each "
			$RelatedWorkItems = getSuiteRelations -vstsAccount $vstsAccount -projectName $projectName -SuiteID $SuiteID -user $user -token $token
			$LinkedPBIs = @()
			foreach ($RelatedWorkItem in $RelatedWorkItems)
			{
				$WorkItemDetails = getWorkItemType -vstsAccount $vstsAccount -projectName $projectName -WorkItemId $RelatedWorkItem -user $user -token $token
				if($WorkItemDetails.contains("User Story"))
				{
					$LinkedPBIs += $RelatedWorkItem
					$PBIArea = $WorkItemDetails.split(";")[1]
					$PBIIteration = $WorkItemDetails.split(";")[2]
				}	
			}
			
			
			#Creating bug under suite
			#$BugTitle='Automated_Bug_IDP#'+$Env:BUILD_NO+"_TestRun#"+$TestRunID
			$failedTestIds = $SuiteIDs[$SuiteID]
			$BugID = CreateBugUnderWorkItem -vstsAccount $vstsAccount -projectName $projectName -user $user -token $token -BugTitle $BugTitle -WorkItemId $SuiteID -LogFileName $LogFileName -failedTestIds $failedTestIds -environment $Env:ENVIRONMENT -idpBuildNo $Env:BUILD_NO -RunId $TestRunID -IDPReleaseNo $IDPReleaseNo -Priority $Priority -Severity $Severity -AreaPath $PBIArea -IterationPath $PBIIteration
				
			   
			foreach($PBI in $LinkedPBIs)
			{
			#Linking Bug with the PBI
			UpdateBugLinkWithWorkItem -BugId $BugID -PBI $PBI -vstsAccount $vstsAccount -projectName $projectName -user $user -token $token
			Write-Message $LogFileName "[Log] :Linked bug: $BugID with PBI: $PBI"
			}
			
		}
		ZipResultFiles
		AttachResultsToRun -user $user -token $token -vstsAccount $vstsAccount -projectName $projectName -runId $TestRunID -LogFileName $LogFileName
		Write-Message $LogFileName "[Log] : Test cases failed. Exiting the script"
		Exit(1)
		#Write-Message $LogFileName "[Log] : Script has been executed successfully"
	}
}
Catch
{
    $ErrorMessage = $_.Exception.Message
    Write-Message $LogFileName "Error while Creating a Bug in VSTS for Work Item : $WorkItemId"
	Write-Message $LogFileName "[Error] : $_.Exception.Message"
	Exit(1)
}
}
#End of IntegrateIDP_TestHub
IntegrateIDP_TestHub
