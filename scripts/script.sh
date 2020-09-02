exec &> ./scripts/log.log
./scripts/ffmpeg -i ./sound/merge.wav -vn -ar 44100 -ac 2 -b:a 192k ./sound/output.mp3