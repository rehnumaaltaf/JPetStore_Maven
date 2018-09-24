# Script to get agent machine details
#--------- Code to create a unique name for the Shared Path ----------
$DateTime = Get-Date
$Date = $DateTime.ToString("ddMMyyy")
$TimeInHour = $DateTime.Hour
$TimeInMinutes  = $DateTime.Minute
$TimeInSeconds = $DateTime.Second
$UniqStringAppend = "$Date$TimeInHour$TimeInMinutes$TimeInSeconds"
$ShareName = "reports_share"  + "$UniqStringAppend"
#---------------------------------------------------------------------

#-------- Writing the Values to the VSTS logs to be retrieved later --
Write-Host "Agent_MachineName:$env:Agent_MachineName"
Write-Host "Build_SourcesDirectory:$env:Build_SourcesDirectory"
Write-Host "Shared_Path_UName:$ShareName"
#---------------------------------------------------------------------

#-------- Sharing the reports path to be accessed from Jenkins Job ---
if(!(Test-path "\\$env:Agent_MachineName\$ShareName"))
{
    $sourceDirectory = $env:Build_SourcesDirectory+ "\" + $env:IDP_TEST_REPORT_PATH
    net share $ShareName=$sourceDirectory "/GRANT:Everyone,Read" "/GRANT:$env:user,CHANGE" /REMARK:"Home folder for $env:Build_SourcesDirectory" 
}
#---------------------------------------------------------------------
