#!/bin/bash
# ---------- CLIENT  -----------
INPUTFILE="./input/userInput.in"
read -p 'Username: ' userName
read -sp 'Password: ' password
KEYSTOREPATH="./certs/client/${userName}keystore"
TRUSTSTOREPATH="./certs/client/${userName}truststore"

echo $userName > $INPUTFILE
echo -e '.\n.\n.\n.\nSE\ny' >> $INPUTFILE
echo -e '\n'

# Creates clienttruststore.
echo 'y' | keytool -import -file ./certs/CA/public_CA_key.pem -alias CA -keystore $TRUSTSTOREPATH -storepass $password

keytool -keystore $KEYSTOREPATH -genkey -alias CKS -storepass $password < $INPUTFILE

# Create the request for client to get certified. Generates one file
keytool -keystore $KEYSTOREPATH -certreq -alias CKS -keyalg rsa -file ./certs/client/${userName}Request.csr -storepass $password

# Signs the request.
openssl x509 -req -CA ./certs/CA/public_CA_key.pem -CAkey ./certs/CA/private_CA_key.pem -in ./certs/client/${userName}Request.csr -out ./certs/client/${userName}Signed.cer -days 365 -CAcreateserial

echo 'y' | keytool -import -keystore $KEYSTOREPATH -file ./certs/CA/public_CA_key.pem -alias CA -storepass $password

echo 'y' | keytool -import -keystore $KEYSTOREPATH -file ./certs/client/${userName}Signed.cer -alias CKS  -storepass $password

# Clean up
rm ./certs/client/${userName}Request.csr
rm ./certs/client/${userName}Signed.cer

# Examine keystore.
# echo "password" | keytool -keystore clientkeystore -list -v
