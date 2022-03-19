import requests
import time


start = time.time()
for i in range(100):
  url = "http://localhost:8080/upload"

  files = {'file': open('8k.jpg','rb')}


  requests.post(url, files=files)

end = time.time()
print("Elapsed time during the whole program in seconds:",
                                         end-start)
