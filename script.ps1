# Ruta del jar del servidor y cliente
$SERVER_JAR = "out\artifacts\server_jar3\grpc-hello-server.jar"
$CLIENT_JAR = "out\artifacts\client_jar\grpc-hello-server.jar"



# Definir la cantidad de clientes y tamaños de datos
$clientCounts = @(12)
$dataSizes = @(10000)

foreach ($clientCount in $clientCounts) {
    foreach ($dataSize in $dataSizes) {
        # Iniciar el servidor en segundo plano
        Write-Host "Iniciando el servidor..."
        $serverProcess = Start-Process -NoNewWindow -FilePath "java" -ArgumentList "-jar $SERVER_JAR" -PassThru

        # Esperar 5 segundos para asegurarse de que el servidor esté en marcha
        Write-Host "Esperando 5 segundos para que el servidor se inicialice..."
        Start-Sleep -Seconds 5

        # Generar clientes con diferentes nombres
        for ($i = 1; $i -le $clientCount; $i++) {
            $CLIENTNAME = "client$i"
            Write-Host "Ejecutando cliente: $CLIENTNAME con tamaño $dataSize"
            Start-Process -NoNewWindow -FilePath "java" -ArgumentList "-jar $CLIENT_JAR $CLIENTNAME $dataSize"
        }

        # Esperar a que los clientes terminen (ajusta el tiempo de espera según sea necesario)
        Write-Host "Esperando a que los clientes terminen..."
        Start-Sleep -Seconds 20

        # Detener el servidor (matar el proceso Java)
        Write-Host "Deteniendo el servidor..."
        Stop-Process -Id $serverProcess.Id -Force

        Write-Host "Todos los procesos han terminado para $clientCount clientes con tamaño de datos $dataSize.`n"
    }
}
