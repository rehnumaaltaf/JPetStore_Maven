Param(
   [string]$vstsAccount = "sparktrial",
   [string]$projectName = "OnlineTestApp",
   [string]$DefinitionId = 20,
   [string]$user = "T967545",
   [string]$token = "s6qxezuhnvmsge4buzgo4drwskpbdjmr7bh2xxbvuiahkhkarb4q",
   [string]$BugTitle = 'Automated_Bug_Creation_IDP#17' ,
   [string]$AssignedTo = "t967545@spark.co.nz",
   [string]$WorkItemId = "2668"
   
)

$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $user,$token)))
$collection="https://"+$vstsAccount+".visualstudio.com/DefaultCollection"
#$TestName = Get-Date -format "yyyyMMdd.HHmmss"
#$TestName = "TestRunFromIDP" + $TestName

$Body ="
[	
	{
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/System.Title`"`,
		`"value`"` : `"$BugTitle`"`
	},
	{
		`"op`"` : `"add`"`,
		`"path`"` : `"/fields/System.AssignedTo`"`,
		`"value`"` : `"$AssignedTo`"`
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

Write-Host "BODY IS : $Body"

#Converting the String to Json Object.
#$BodyJson = @($Body) | ConvertTo-Json
#$URI = "$collection"+"/"+"/_apis/build/definitions?api-version=1.0"
$URI = "$collection"+"/"+$projectName+"/_apis/wit/workitems/$"+"bug?api-version=1.0"
#Calling the REST-API to Run the Test
write-host $URI
invoke-restmethod  -Uri $URI -Method PATCH -ContentType "application/json-patch+json" -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -Body $Body 

