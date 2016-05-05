///*
// * 判断是否为登录状态
// * 已登录，显示用户欢迎
// * 未登录，显示登录注册
// */
//function checkLogin() {
//	$.ajax({
//		type: "post",
//		url: "checkLogin.do",
//		success: function(data) {
//			if (data.isLogin) {
//				document.getElementById("user").innerText = data.user.name;
//				document.getElementById("welcom").show();
//				document.getElementById("users").show();
//			} else {
//				document.getElementById("login").show()
//			}
//		},
//		async: true
//	});
//}
///*
// * 加载用户列表
// */
//function loadUsersList() {
//	$.ajax({
//		type: "get",
//		url: "users.do",
//		success: function(data) {
//			var users = data.users;
//			var html = "<tr><th>id</th><th>用户名</th><th>密码</th></tr>";
//			if (users != null) {
//				$(users).each(function(i, e) {
//					html += "<tr><td>" + e.id + "</td><td>" + e.name + "</td><td>" + e.password + "</td></tr>"
//				})
//			}
//			$("#users").html(html);
//		},
//		async: true
//	});
//}

$(document).ready(function() {
	BUI.use(['bui/overlay', 'bui/grid', 'bui/data'], function(Overlay, Grid, Data) {

		var Grid = Grid,
			Store = Data.Store,
			columns = [{
				title: 'ID',
				dataIndex: 'id',
				width: 100
			}, {
				title: '用户名',
				dataIndex: 'name',
				width: 200
			}, {
				title: '密码',
				dataIndex: 'password',
				width: 300
			}],
			data = [{
				id: '1',
				name: '123',
				password: '123'
			}, {
				id: '2',
				name: 'edd'
			}, {
				id: '3',
				name: 'eee',
				password: "111"
			}];

		var store = new Store({
				data: data,
				autoLoad: true
			}),
			grid = new Grid.Grid({
				render: '#users',
				forceFit: true, // 列宽按百分比自适应
				columns: columns,
				store: store
			});

		grid.render();

		$('#loginShow').on('click', function() {
			var dialog = new Overlay.Dialog({
				title: '登录',
				width: 400,
				height: 200,
				//配置DOM容器的编号
				contentId: 'login',
				buttons: [{
					text: 'LOGIN',
					elCls: 'button button-primary',
					handler: function() {
						//do some thing
						$.ajax({
							type: "post",
							url: "login.do",
							data: $("#login").serialize(),
							success: function(data) {
								//登录成功
								$("#user").html(data.user.name);
								$("#nologin").hide(); //隐藏登录注册按钮
								$("#welcom").show(); //显示欢迎语
								
								//加载用户信息
								$.ajax({
									type: "post",
									url: "users.do",
									success: function(data) {
										var store = new Store({
												data: data,
												autoLoad: true
											}),
											grid = new Grid.Grid({
												render: '#users',
												forceFit: true, // 列宽按百分比自适应
												columns: columns,
												store: store
											});

										grid.render();
									},
									async: true
								});

								this.close();
							},
							async: true
						});
						$("#nologin").hide();
						$("#welcom").show();
						this.close();
					}
				}],
			});
			dialog.show();
		});
	})

})