##  金州勇士

## 资料

[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

##工具




##脚本
'''sql
create TABLE user(
    id int auto_increment primary key ,
    account_id varchar(100),
    name varchar(50),
    token char(36),
    gmt_create BIGINT,
    gmt_modified bigint
);

'''