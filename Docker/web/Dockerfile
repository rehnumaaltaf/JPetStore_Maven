
FROM docker/whalesay:latest
LABEL Name=web Version=0.0.0 
RUN apt-get -y update && apt-get install -y fortunes
CMD /usr/games/fortune -a | cowsay
