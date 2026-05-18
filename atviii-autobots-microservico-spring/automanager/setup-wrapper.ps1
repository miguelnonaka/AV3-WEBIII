$jarUrl = "https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"
$jarPath = ".mvn\wrapper\maven-wrapper.jar"

Write-Host "Baixando Maven Wrapper JAR..." -ForegroundColor Green
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12

try {
    (New-Object System.Net.WebClient).DownloadFile($jarUrl, $jarPath)
    Write-Host "JAR baixado com sucesso!" -ForegroundColor Green
    Get-Item $jarPath | Select-Object Name, @{Name="Tamanho (KB)";Expression={[math]::Round($_.Length/1KB,2)}}
    
    Write-Host "`nExecutando: mvnw spring-boot:run" -ForegroundColor Green
    & .\mvnw.cmd spring-boot:run
} catch {
    Write-Host "ERRO: $($_)" -ForegroundColor Red
}
