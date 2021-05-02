<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MyCommentList</title>
</head>
<body>
<form action="../userHome/${user.id}" align="right">
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
    status: ${comment.status}
    <br>
<#--    <a href="../editComment/${user.id}/${comment.id}">update</a>     <a-->
<#--        href="../deleteComment/${user.id}/${comment.id}">delete</a>-->
    <form action=" ../editComment/${user.id}/${comment.id}">
        <button>edit</button>
    </form>
    <form action="../deleteComment/${user.id}/${comment.id}" method="post">
        <button>delete</button>
    </form>
    <br>
    <br>
</#list>
</body>
</html>