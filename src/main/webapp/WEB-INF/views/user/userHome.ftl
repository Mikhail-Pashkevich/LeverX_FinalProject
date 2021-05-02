<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h1>user home</h1>

<p>hello ${user.login}</p>

<form action="/" align="right">
    <button>Sign out</button>
</form>
<form action="../myCommentList/${user.id}" align="right">
    <button>My comments</button>
</form>
<form action="../traderList/${user.id}" align="right">
    <button>Show Traders</button>
</form>
</body>
</html>