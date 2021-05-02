<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UserList</title>
</head>
<body>
<form action="../adminHome/${admin.id}" align="right">
    <button>Home</button>
</form>
<table>
    <tr>
        <th>login</th>
        <th>email</th>
        <th>status</th>
    </tr>

    <#list users as user>
        <tr>
            <td>${user.login}   </td>
            <td>${user.email}   </td>
            <td>${user.status}   </td>
            <td>
                <form action="../blockUser/${admin.id}/${user.id}" method="post">
                    <button>block</button>
                </form>
            </td>
            <td>
                <form action="../unblockUser/${admin.id}/${user.id}" method="post">
                    <button>unblock</button>
                </form>
            </td>
        </tr>
    </#list>

</table>
</body>
</html>