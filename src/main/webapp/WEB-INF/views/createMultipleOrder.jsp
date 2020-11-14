<html lang="en">
<head>
    <title>New order</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script type="text/javascript" src="/res/multipleOrderCreationScript.js"></script>
</head>
<body>
<div class="container-xl">
    <form name="newMultipleOrder" method="POST" onsubmit="createMultipleOrder()">
        <label>Just press the button</label>
        <button id="submit" type="submit" class="btn btn-primary">Create multiple order</button>
    </form>
</div>
</body>
</html>