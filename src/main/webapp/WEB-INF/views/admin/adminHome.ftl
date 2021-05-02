<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h1>admin home</h1>
<form action="/" align="right">
    <button>Sign out</button>
</form>
<br>
<form action="../commentCheckList/${admin.id}" align="right">
    <button>Comments for check</button>
</form>
<br>
<form action="../userList/${admin.id}" align="right">
    <button>Show Users</button>
</form>
</body>
</html>