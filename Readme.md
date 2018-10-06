![alt-text](https://raw.githubusercontent.com/MilchReis/NetPetcam/master/Logo.png "Logo")

The NetPetcam is a simple solution to capture continuously pictures of your pets and save it to your personal dropbox. In that way you are able to check your pets from everywhere without router port forwarding.

## Man-Page ##

```
usage: java -jar NPetcam.jar
 -c,--configfile <arg>   Sets the path to a configfile
 -h,--help               Prints this help
 -i,--interval <arg>     Sets the capture interval in seconds
 -m,--max-images <arg>   Sets the maximum of saved images in the dropbox
 -r,--rpi-mode           Sets the raspberry pi mode for capture with rpicam
 -s,--stream             Instead of Dropbox image, stream to localhost:8080
 -t,--token <arg>        Sets the dropbox access token
```

## Download and run ##

The simplest way to run NetPetcam is to download the jar-file and execute it. Feel free to [download](https://github.com/Milchreis/NetPetcam/releases) it.

With the command `java -jar NPetcam.jar -c /path/to/your/npetcam.properties` you start the program.

Your properties file can look like this:
```
# Set up the dropbox access token
dbxtoken=

# Set up the shooting and uploading interval in seconds 
interval=5

# Set the maximum number of image which are stored in the dropbox
maximages=10
```

## Remote configuration
If you want to change the interval or maximages settings from remote, than you can create a directory "admin" in your dropbox app directory and place a config.properties file like this:
```
# Set up the shooting and uploading interval in seconds 
interval=5

# Set the maximum number of image which are stored in the dropbox
maximages=10
```
NetPetcam will find this file and changes the settings locally.

## Build
If you want to build the program for your self, you can use maven like this:
Goals: `clean compile assembly:single`

## Dependencies

To run this program correctly you need the following things:

 - Java Runtime Environment 8 (JRE-8) or higher
 - Dropbox-account and an access-token (don't worry is free and easy, look [here](https://blogs.dropbox.com/developers/2014/05/generate-an-access-token-for-your-own-account/))
 - Webcam or Rapsberry Pi + Pi-Cam
 - Pets :-)
