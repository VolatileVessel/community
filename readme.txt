readme
每次你修改了你的项目（abc文件里面的东西修改了，这里的修改包括修改代码、添加文件、删除文件），都需要执行这几步命令（如下所示），在github上面更新

git add .

git commit -m "备注"

git push

上面的git add . 一定不要忘记有个点

git add . 和 git commit -m "备注" ， 可以合并成为git commit -a -m "备注"

 15248668333
 
git 版本冲突，解决。
《《《 HEAD
冲突模块
======
冲突模块
>>>> sasdfsdfwerwqerwe
,git pull 手动解决完冲突后
重新提交代码


add community readme.txt

add demo readme.txt
4.关于冲突的个人心得
　　多人协作开发的时候,如果出现了你没有改过的文件跟你冲突了,一定要去找到当事者,说清楚是如何冲突的；

　　然后协商解决,千万不要擅自拉别的分支去试图解决冲突,或找文件覆盖，更或者以自己的文件为准.

   多人协作开发的时候,如果出现了你没有改过的文件跟你冲突了,一定要去找到当事者,说清楚是如何冲突的；

　　然后协商解决,千万不要擅自拉别的分支去试图解决冲突,或找文件覆盖，更或者以自己的文件为准.

　　同时记住,解决了之后,要add 和 commit 最后push.为保证万无一失,最后在冲突都解决之后,重启项目；

　　保证至少不会有立即奔溃的现象发生.然后才去提交,push.
Community demotest
Community demotest
Community demotest
Community demotest
Community demotest
Community demotest
