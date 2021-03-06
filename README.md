# LawnMower

This is a program for the company X and what it does it is to implement algorithm to mow rectangular surfaces.
LawnMower will control sequentially N lawn mowers, each one following directives from a configuration file.
In the directory [input_files](https://github.com/tul1/LawnMower/tree/master/input_files) you'll find examples and documentation of the input files that LawnMower consumes. 
LawnMower will output the final position and orientation of each mower in the order that the mower appeared in the input.

## Build and run LawnMower
### Build with Maven
To build the project just run the following command:
```bash
$mvn package
```
To perform this you'll have to install Maven:3.+.
This operation will create a directory named `target` where the java package will be contained.

### Build with Docker
If you don't want to install Maven and all its dependencies you always can grab the official Maven's image from dockerhub and run it as it's shown bellow:
```bash
$docker login
$docker pull maven:3.3-jdk-8
$docker run -it --rm --name LawnMower -v "$(pwd)":/usr/src/LawnMower -w /usr/src/LawnMower maven:3.3-jdk-8 mvn clean install
```
You must install docker to take this building option.
After running the above commands the directory `target` will be created containing the java package of LawnMower.

### Run LawnMower
Once the package is built you'll just have to run the following command:
```bash
$java -cp target/LawnMower-1.0.jar App input_file.txt
```
An SDK of Java8 or greater must be installed in your system to run LawnMower.

### Run LawnMower: production version
To run a production version LawnMower you just have to run the following commands: 
```bash
$docker login
$docker pull tul1/lawnmower:tagname
$docker run -v ./input_files:/app/input_files -t tul1/lawnmower:latest java -cp target/LawnMower-1.0.jar App /app/input_files/input_file.txt
```

Production version are based on LawnMower releases.
Alternatively you can access the container with the interactive mode and run the LawnMower inside, with the following commands:
```bash
$docker login
$docker pull tul1/lawnmower:tagname
$docker run -it -v ./input_files:/app/input_files -t tul1/lawnmower:latest bash
#java -cp target/LawnMower-1.0.jar App /app/input_files/input_file.txt
```

## LawnMower Releases
* [v1.0.0](https://github.com/tul1/LawnMower/releases/tag/v1.0.0): Bulbasaur

## How to contribute
To contribute with our project you have just to create an Issue attached to one of our Milestone and create a Pull Request with your code linked to your Issue.
To ask a review, your PR must pass the following criteria:
* All tests Passing!
* Coverage over 90%
