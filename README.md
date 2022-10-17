# roland-garros

mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.war)
docker build -t ineed/roland-garros .
docker run -p 8080:8080 ineed/roland-garros