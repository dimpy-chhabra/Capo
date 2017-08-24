<?php
	$hostname = "mysql.hostinger.in";
	$username = "u714414987_user";
	$password = "pass12345";
	$dbname = "u714414987_capo";

	// Create connection
	$conn = new mysqli($hostname, $username, $password, $dbname);

	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	} 
	$mob = $_POST['mob'];
	//$mob = "8586847364";

	$sql = "SELECT u_pass FROM user_details WHERE u_id = '$mob' ";

	$result = $conn->query($sql);
	if ($result->num_rows > 0) {
		while($row = $result->fetch_assoc()) { 
		 echo $row["u_pass"];
		}
	}else{
		 echo "Err";
	}

	
	$conn->close();
?>
