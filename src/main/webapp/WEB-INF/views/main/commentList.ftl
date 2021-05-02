<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CommentList</title>
</head>
<body>
<h1>Comments</h1>
<form action="/" align="right">
    <button>Home</button>
</form>
<#list comments as comment>
    trader: ${comment.trader.login}
    <br>
    author: ${comment.author.login}
    <br>
    message: ${comment.message}
    <br>
    created at: ${comment.createdAt}     updated at: ${comment.updatedAt}
    <br>
    <br>
</#list>
</body>
</html>