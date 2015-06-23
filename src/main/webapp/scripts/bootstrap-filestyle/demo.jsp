<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@ include file="/include/ext2js.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>Testing - Bootstrap FileStyle</title>

		<link rel="icon" type="image/png" href="icons/favicon.png">
		<link href="${ctx}/scripts/bootstrap-filestyle/css/bootstrap.min.css" rel="stylesheet">

		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		<div class="container" style="margin-top: 30px;margin-bottom: 30px">
			<div class="row">
				<div class="span12">
					<h2 style="text-align: center;">Testing - Bootstrap FileStyle</h2>
					<div class="well">
						<form role="form" action="upload.php" enctype="multipart/form-data" method="post">
							<h3>Testing Upload</h3>
							<hr>
							<div class="row">
  								<div class="col-xs-6">
									<div class="form-group">
										<input type="file" class="filestyle" data-buttonName="btn-primary">
									</div>
								</div>
								<div class="col-xs-2">
									<div class="form-group">
										<button type="submit" class="btn btn-primary">
											<span class="glyphicon glyphicon-ok"></span> Send
										</button>
									</div>
								</div>
							</div>
						</form>
					</div>
					
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${ctx}/scripts/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/bootstrap-filestyle/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/bootstrap-filestyle/js/bootstrap-filestyle.js"></script>
		<script type="text/javascript">
		$(":file").filestyle({buttonName: "btn-primary"});
		</script>
	</body>

</html>

