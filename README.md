## 菜鸟的java学习笔记

![java: 1.8 (shields.io)](https://img.shields.io/badge/jdk-1.8-green)



## 2022-9-17:

- 新增image文件夹主要是为了之前有些图片存在了C盘，导致git上无法查看图片
- image 涉及的笔记有：javaWeb、spring



## Clone

```
git clone git@github.com:dangHuifeng/java.git
```



## Error

- commit Netty heima 2022-9-23 应为 2022-9-13【已解决】
```
git rebase -i HEAD~x
```



### 知识小贴士

```html
<body>
<div class="custom-block tip">
    <p class="custom-block-title">知识小贴士：</p>
    <p><code style="color: red">MERGE_THRESHOLD</code>：合并页的阈值，可以自己设置，在创建表或者创建索引时指定。</p>
</div>
</body>
<style>
    .custom-block .custom-block-title {
         font-weight: 600;
         margin-bottom: .2rem;
     }

    .custom-block.tip{
        border-radius: .4rem;
        padding: .5rem 1.5rem;
        border-left-width: .5rem;
        border-left-style: solid;
        background-color: #f3f5f7;
        border-color: #42b983;
        /*warn*/
        /*border-color: red;*/
        color: #215d42;
    }
</style>
```

<div style="border-radius: .4rem;
            padding: .5rem 1.5rem;
            border-left-width: .5rem;
            border-left-style: solid;
            background-color: #f3f5f7;
            border-color: #42b983;
            color: #215d42;
            font-weight: 600;">
    <p class="custom-block-title">知识小贴士：</p>
    <p><code style="color: red">code</code>：something。</p>
</div>

<div style="border-radius: .4rem;
            padding: .5rem 1.5rem;
            border-left-width: .5rem;
            border-left-style: solid;
            background-color: #f3f5f7;
            border-color: red;
            color: #215d42;
            font-weight: 600;">
    <p class="custom-block-title">注意：</p>
    <p><code style="color: red">code</code>：something。</p>
</div>

