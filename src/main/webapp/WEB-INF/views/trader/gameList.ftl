<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GameList</title>
</head>
<body>
<form action="/traderHome" align="right">
    <button>Home</button>
</form>
<table>
    <tr>
        <th>name</th>
    </tr>

    <#list games as game>
        <tr>
            <td>${game.name}   </td>
            <td><a href="addComment/${game.id}">add</a></td>
        </tr>
    </#list>

</table>
</body>
</html>