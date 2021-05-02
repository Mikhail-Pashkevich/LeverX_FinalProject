<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TraderList</title>
</head>
<body>
<h1>Traders</h1>
<form action="../userHome/${user.id}" align="right">
    <button>Home</button>
</form>
<table>
    <tr>
        <th>login</th>
        <th>email</th>
    </tr>

    <#list traders as trader>
        <tr>
            <td>${trader.login}   </td>
            <td>${trader.email}   </td>
            <td><a href="../addComment/${user.id}/${trader.id}">comment</a></td>
        </tr>
    </#list>

</table>
</body>
</html>