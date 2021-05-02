<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CommentCheckList</title>
</head>
<body>
<form action="../adminHome/${admin.id}" align="right">
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
    <form action=" ../approveComment/${admin.id}/${comment.id}" method="post">
        <button>approve</button>
    </form>
    <form action="../deleteComment/${admin.id}/${comment.id}" method="post">
        <button>delete</button>
    </form>
    <br>
    <br>
</#list>
</body>
</html>