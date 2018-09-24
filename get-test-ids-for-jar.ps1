Param(
   [string]$vstsAccount = "sparknz",
   [string]$projectName = "",
   [string]$user = "svc_cicd",
   [string]$token = "di5in3rrbkgmnbto6hsfloxokb5x6muzqinnmybofa3y7xtqg6ya",
   [string]$planId =$Env:testPlan,
   [string]$suiteIdList =$Env:testSuite
   )

Write-host "[Log] Project $projectName PlanID $planId SuiteID $suiteIdList"


Function GetTestCaseIds
{
[CmdletBinding(SupportsShouldProcess=$true)]
Param(
   [string]$vstsAccount,
   [string]$projectName,
   [string]$user ,
   [string]$token ,
   [string]$planId,
   [string]$suiteIdList
   
)


$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))
$collection="https://sparknz.visualstudio.com/DefaultCollection"

#getting suite ids in a list
$suite=$suiteIdList -split ","
$testId_array=@()
foreach ($suiteId in $suite)
{

$URI = "$collection"+"/"+$projectName+"/_apis/test/plans/$planId/suites/$suiteId/testcases?api-version=2"
Write-Host "---------------------------------------------------------------------------------------------------------------------"
Write-Host "[LOG] User : $user had triggered the build definition for Test Plan : $planId and Suite ID : $suiteIdList" for $projectName in $URI
Write-Host "---------------------------------------------------------------------------------------------------------------------"

#Calling the REST-API to Get the TestCase Ids within the Suite
$response=Invoke-WebRequest  -Uri $URI -Method GET  -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} 
$JsonContent=$response.Content
$ResponseValue = $response | ConvertFrom-Json
if($ResponseValue.count -ne '0'){
#calling JSON Parser method to get the test ids 
$key=Get-ObjectMembers $JsonContent "testCase" | ConvertTo-Json
$testId_array+=Get-ObjectMembers $key "id"
}
}


$testId_array=$testId_array| select -uniq

foreach($id In $testId_array){
$testCaseId_list+="$id " 
}
return $testId_array
#return $testCaseId_list


}

#calling main function.Returns the list of test Ids

function Get-ObjectMembers 
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

       $json = $jsonObj | ConvertFrom-Json
       [string[]]$Keys = $($json| Get-Member -MemberType *Property).Name

       #Write-Host "[LOG] The Passed Key  is : $keyValue"
       #Write-Host "-----------------------------------------------"
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

#Invoke-Item (start-powershell ((split-path $MyInvocaton.InvocationName)+" C:\Users\T967545Downloads\testscript\Untitled4.ps1"))

$TestIdString=GetTestCaseIds -vstsAccount $vstsAccount -projectName $projectName -user $user -token $token -planId $planId -suiteIdList $suiteIdList
Write-Host "[LOG] The Key Value passed to jar is $TestIdString "
& java -jar ./MTMJsonReaderWritter.jar $TestIdString
