![alt text](https://Logo.png "Logo")

The NETPetcam is a simple solution to capture continuously pictures of your pets and save it to your personal dropbox to check your from everywhere. This project was born for my pets 

## Man-Page ##

´´´
usage: NPetcam.jar
 -c,--configfile <arg>   Sets the path to a configfile
 -h,--help               Prints this help
 -i,--interval <arg>     Sets the capture interval in seconds
 -m,--max-images <arg>   Sets the maximum of saved images in the dropbox
 -p,--wifipass <arg>     Sets the WPA2 key for the WIFI access point
 -r,--rpi-mode           Sets the raspberry pi mode for capture with
                         rpicam
 -s,--ssid <arg>         Sets the ssid for the WIFI access point
 -t,--token <arg>        Sets the dropbox access token
 ´´´

## Download / Build / Run ##

The simplest way to run picsort is to download the build for your platform. The latetest builds of the project is hosted on github. Feel free to [download](https://github.com/MilchReis/PicSort/tree/master/bin "download-address") it. Until now windows and linux are supported.

With the command `java -jar NPetcam.jar -c /path/to/your/npetcam.properties` you start the program.

Your properties file can look like this:

´´´
# Set up the dropbox access token
dbxtoken=

# Set up your WIFI credentials to login to your local WIFI
ssid=
wlanpw=

# Set up the shooting and uploading interval in seconds 
interval=5

# Set the maximum number of image which are stored in the dropbox
maximages=10
´´´

## Dependencies

To run this program correctly you need the following things:

 - Java Runtime Environment 8 (JRE-8)
 - Dropbox-account and an access-token (don't worry is free and easy :))
 - Webcam or Rapsberry Pi + Pi-Cam
 - Pets :)