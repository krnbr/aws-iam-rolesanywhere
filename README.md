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

### coming up details on how to create cert, encode cert, key, etc.
