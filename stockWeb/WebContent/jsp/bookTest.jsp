<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>环境搭建测试</title>
</head>
<body>
	<h1>环境搭建测试</h1>
	<form action="/stockWeb/saveBook.do" method="post">
		<table class="table" style="width: 100%; height: 100%;">
			<tr>
				<th width="100">图书名称</th>
				<td>
					<input type="text" name="name" value="" style="width: 210px;" />
				</td>
			</tr>
			<tr>
				<th>图书编号
				</th>
				<td>
					<input type="text" name="sn" value="" style="width: 210px;" />
				</td>
			</tr>
			<tr>
				<th>售价
				</th>
				<td>
					<input type="text" name="price" value="" style="width: 210px;" />
				</td>
			</tr>
			<tr>
				<th>
					<input type="submit">
				</th>
				<td>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>