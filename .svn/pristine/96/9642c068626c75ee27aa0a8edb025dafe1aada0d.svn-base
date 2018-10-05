![alt-text](https://raw.githubusercontent.com/MilchReis/NetPetcam/master/Logo.png "Logo")

The NETPetcam is a simple solution to capture continuously pictures of your pets and save it to your personal dropbox. In that way you are able to check your pets from everywhere.

## Man-Page ##

```
usage: java -jar NPetcam.jar
 -c,--configfile <arg>   Sets the path to a configfile
 -h,--help               Prints this help
 -i,--interval <arg>     Sets the capture interval in seconds
 -m,--max-images <arg>   Sets the maximum of saved images in the dropbox
 -p,--wifipass <arg>     Sets the WPA2 key for the WIFI access point
 -r,--rpi-mode           Sets the raspberry pi mode for capture with
                         rpicam
 -s,--ssid <arg>         Sets the ssid for the WIFI access point
 -t,--token <arg>        Sets the dropbox access token
```

## Download / Run / Build ##

The simplest way to run NETPetcam is to download the jar-file and execute it. Feel free to [download](https://github.com/MilchReis/NetPetcam/raw/master/bin/NPetcam-1.0.1.jar "download-address") it.

With the command `java -jar NPetcam.jar -c /path/to/your/npetcam.properties` you start the program.

Your properties file can look like this:
```
# Set up the dropbox access token
dbxtoken=

# Set up the shooting and uploading interval in seconds 
interval=5

# Set the maximum number of image which are stored in the dropbox
maximages=10


# Set up your WIFI credentials to login to your local WIFI, if you want
# Its neccessary for the rapsberry pi
ssid=
wlanpw=
```

If you want to build the program for your self, you can use maven like this:
Goals: `clean compile assembly:single`

## Dependencies

To run this program correctly you need the following things:

 - Java Runtime Environment 8 (JRE-8)
 - Dropbox-account and an access-token (don't worry is free and easy :))
 - Webcam or Rapsberry Pi + Pi-Cam
 - Pets :)
