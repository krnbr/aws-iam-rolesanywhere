### Intro

This post aligns with the blog post here = https://neuw.medium.com/spring-boot-aws-roles-anywhere-quick-guide-fb9e85db9c20?sk=957ad57866d702cccd861d7cade4b234

It can cover good number of scenarios, detailed below.

### Prerequisites

 - setup an AWS account
 - setup an IAM role
 - setup AWS roles anywhere trust anchor & profile
 - setup certificate and key.
 - setup properties in the `/src/main/resources/application.properties`

### Scenarios

| Key type                              | Tested | Description                                                                    | 
|---------------------------------------|--------|--------------------------------------------------------------------------------|
| EC                                    | Yes    | When the certificate algorithm = EC                                            |
|                                       |        |                                                                                |
| RSA                                   | Yes    | When the certificate algorithm = RSA                                           |
|                                       |        |                                                                                |
| RSA with one intermediate             | Yes    | When the certificate algorithm = RSA with an intermediate as part of the chain |
|                                       |        |                                                                                |
| EC with one intermediate              | Yes    | When the certificate algorithm = EC with an intermediate as part of the chain  |
|                                       |        |                                                                                |
| EC or RSA with more than intermediate | NO     | Very rare scenario, code can be changed to accumulate that as well             |
|                                       |        |                                                                                |


### Openssl Commands

**example root-ca.cnf & client.cnf files are placed in openssl-conf folder**

#### Root Certificate 

```shell
# example root-cnf file in openssl-conf folder

# create key
openssl genrsa -out root-ca.key 2048

# validate key
openssl rsa -check -noout -in root-ca.key

# create certificate
openssl req -x509 -new -nodes -key root-ca.key \
    -subj "/CN=Roles Anywhere Trust CA/O=Test Limited/ST=Punjab/L=Mohali/C=IN" \
    -sha256 \
    -days "3650" \
    -extensions ca_ext \
    -config root-ca.cnf \
    -out root-ca.pem
```

#### Create RSA Key & RSA based Certificate

```shell
# create a client key named as client.key
openssl genrsa -out client.key 2048

# create a csr
openssl req -new -key client.key -out client.csr -config client.cnf

# create a client certificate for 365 days.
openssl x509 -req -in client_cert.csr -CA root-ca.pem -CAkey root-ca.key -CAcreateserial -out client_cert.crt -days 365 -extfile req-client.cnf -extensions v3_req

# validate the certificate
openssl x509 -in client_cert.crt -text -noout
```

#### Create EC Key & EC based Certificate
```shell
# create a client key named as client.key
openssl ecparam -name prime256v1 -genkey -noout -out ec-client.key

# create a csr
openssl req -new -key ec_client.key -out ec_client.csr -config req-client.cnf

# create a client certificate for 365 days.
openssl x509 -req -in ec_client.csr -CA root-ca.pem -CAkey root-ca.key -CAcreateserial -out ec_client.crt -days 365 -extfile client.cnf -extensions v3_req

# validate the certificate
openssl x509 -in client_cert.crt -text -noout
```
