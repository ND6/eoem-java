
# easy java

## orm
 持久层相关技术实践

 ## tutorial
 java 教程

 ## cache
### redis
wsl里安装redis

安装wsl
```shell
wsl --install
```
一路回车，然后重启电脑继续安装，输入想要设置的用户名密码即可。

https://redis.io/docs/getting-started/installation/install-redis-on-windows/

安装redis
1. 进入wsl
```shell
wsl
```
2.下载安装redis
```shell
curl -fsSL https://packages.redis.io/gpg | sudo gpg --dearmor -o /usr/share/keyrings/redis-archive-keyring.gpg

echo "deb [signed-by=/usr/share/keyrings/redis-archive-keyring.gpg] https://packages.redis.io/deb $(lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/redis.list

sudo apt-get update
sudo apt-get install redis
```
3.启动redis
```shell
sudo service redis-server start
```
4.连接到redis
```shell
redis-cli 
127.0.0.1:6379> ping
PONG
```