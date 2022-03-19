import asyncio
from unittest import result
import aiohttp
import time

async def main():
    url = 'http://localhost:8080/upload'
    files = {'file': open('8k.jpg', 'rb')}
    async with aiohttp.ClientSession() as session:
        return await session.post(url, data=files)



#for i in range(100):
#    text = asyncio.run(main())

async def run():
    results = await asyncio.gather(*[main() for i in range(100)])
    


if __name__ == "__main__":
    start = time.time()
    asyncio.run(run())
    end = time.time()
    print("Elapsed time during the whole program in seconds:",
                                         end-start)