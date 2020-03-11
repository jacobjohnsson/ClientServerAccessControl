KEYSTOREPATH="./certs/server/serverkeystore"
TRUSTSTOREPATH="./certs/server/servertruststore"

# ---------- SERVER -----------

# Creates servertruststore.
echo 'y' | keytool -import -file ./certs/CA/public_CA_key.pem -alias CA -keystore $TRUSTSTOREPATH -storepass password

# Creates serverkeystore.
keytool -keystore $KEYSTOREPATH -genkey -alias SKS -storepass password < ./input/serverCert.in

# Create the request for server to get certified. Generates one file
keytool -keystore $KEYSTOREPATH -certreq -alias SKS -keyalg rsa -file ./certs/server/serverRequest.csr -storepass password

# Signs the request.
openssl x509 -req -CA ./certs/CA/public_CA_key.pem -CAkey ./certs/CA/private_CA_key.pem -in ./certs/server/serverRequest.csr -out ./certs/server/serverSigned.cer -days 365 -CAcreateserial


echo 'y' | keytool -import -keystore $KEYSTOREPATH -file ./certs/CA/public_CA_key.pem -alias CA -storepass password


echo 'y' | keytool -import -keystore $KEYSTOREPATH -file ./certs/server/serverSigned.cer -alias SKS  -storepass password

# Examine keystore.
# echo "password" | keytool -keystore serverkeystore -list -v

# Clean up
rm ./certs/server/serverRequest.csr
rm ./certs/server/serverSigned.cer
