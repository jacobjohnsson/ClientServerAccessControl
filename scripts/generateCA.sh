# Creates the public and private key pair for Certfification Autorithy, CA
openssl req -x509 -newkey rsa:2048 -nodes -keyout ./certs/CA/private_CA_key.pem -out ./certs/CA/public_CA_key.pem -days 365 < ./input/createCA.in

# Signs the root certificate, generates clienttruststore amd servertruststore
echo 'y' | keytool -import -file ./certs/CA/public_CA_key.pem -alias CA -keystore ./certs/clienttruststore -storepass password
