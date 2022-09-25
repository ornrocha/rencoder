#!/bin/sh

mkdir $HOME/Rencoder  
cp -r ffmpeg/ $HOME/Rencoder
cp -r icons/ $HOME/Rencoder
cp -r jre/ $HOME/Rencoder
cp -r languages/ $HOME/Rencoder
cp -r profiles/ $HOME/Rencoder
cp -r settings/ $HOME/Rencoder
cp rencoder.jar $HOME/Rencoder


echo "
#!/bin/bash

		JAR_NAME=\"rencoder.jar\"
        cd $HOME/Rencoder/
		APP_HOME=$HOME/Rencoder/
		XMX=-Xmx512m
		XMS=-Xms256M
				 
		\$APP_HOME/jre/bin/java \$XMX \$XMS -jar \$APP_HOME/\$JAR_NAME \"\$@\"

" > $HOME/Rencoder/run.sh

chmod +x $HOME/Rencoder/run.sh


echo "
[Desktop Entry]
Version=1.3.0
Name=Rencoder
Type=Application
Exec=$HOME/Rencoder/run.sh
Terminal=false
Icon=$HOME/Rencoder/icons/rencoderblue.png
Comment=Rencoder is a software package developed in Java language, to be used as a friendly front-end for FFmpeg. This tool can support a wide range of media formats and most of the features present in the FFmpeg.
NoDisplay=false
Categories=AudioVideo;Video
Name[en]=Rencoder
Name[pt]=Rencoder
"> $HOME/.local/share/applications/rencoder.desktop


chown $USER:$USER $HOME/.local/share/applications/rencoder.desktop


echo "Extracted to $HOME/Rencoder"
