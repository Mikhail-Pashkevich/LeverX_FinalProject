<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EditComment</title>
</head>
<body>
<form action="../userHome/${comment.author.id}" align="right">
    <button>Home</button>
</form>
<form method="post">
    <input type="text" size="100" placeholder="your message" value="${comment.message}" name="comment.message">
    <input type="submit" value="edit">
</form>
</body>
</html>