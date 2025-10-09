# JWT Keys Setup

This project uses RSA asymmetric keys to sign and verify JWT tokens.

## Quick Start

Generate the required keys:

```bash
# Create certs directory
mkdir -p src/main/resources/certs

# Generate RSA keypair
openssl genrsa -out src/main/resources/certs/keypair.pem 2048

# Extract public key
openssl rsa -in src/main/resources/certs/keypair.pem -pubout -out src/main/resources/certs/public.pem

# Convert private key to PKCS8 format (required by Java)
openssl pkcs8 -topk8 -inform PEM -outform PEM -in src/main/resources/certs/keypair.pem -out src/main/resources/certs/private.pem -nocrypt
```

## Key Files

```
src/main/resources/certs/
├── keypair.pem    # Original RSA keypair (git-ignored)
├── private.pem    # Private key for signing (git-ignored)
└── public.pem     # Public key for verification
```

The private keys are automatically excluded from git via `.gitignore`.

## Configuration

Keys are configured in `application.properties`:

```properties
app.jwt.private-key=classpath:certs/private.pem
app.jwt.public-key=classpath:certs/public.pem
```

Spring Boot automatically loads these keys into the `ApplicationProperties` record.

## Troubleshooting

**"Invalid key specification"** - Ensure private key is in PKCS8 format (begins with `-----BEGIN PRIVATE KEY-----`)

**"File not found"** - Run the key generation commands above to create the required files
