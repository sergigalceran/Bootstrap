var API_BASE_URL = "http://localhost:8080/myapp/recipe";

$("#button_get_todos").click(function(e) {
	e.preventDefault();
	getTodos();
});

function getTodos() {
	var url = API_BASE_URL + '/list';
	$("#result").text('');
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
			var todos = data.todos;				
				$.each(todos, function(i, v) {
					var todo = v;	
				    $('<br><strong> author: </strong>' + todo.author + '<br>').appendTo($('#result'));
					$('<br><strong> CreationDate: </strong>' + todo.creationDate + '<br>').appendTo($('#result'));
				    $('<br><strong> difficulty: </strong>' + todo.difficulty + '<br>').appendTo($('#result
					$('<br><strong> ingredients: </strong>' + todo.ingredients + '<br>').appendTo($('#result'));
                    $('<br><strong> preparation: </strong>' + todo.title + '<br>').appendTo($('#result'));
					$('<br><strong> title: </strong>' + todo.title + '<br>').appendTo($('#result'));
					});
	}).fail(function() {
		$("#result").text("No data to show.");
	});
}

$("#button_get_todo").click(function(e) {
	e.preventDefault();
	getTodo($("#title").val());
});

function getTodo(tittle) {
	var url = API_BASE_URL + '/' + tittle;
	$("#title").text('');

	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
		var todo = data;
				$("#result").text('');
				$('<strong> author: ' + todo.author + '</strong>').appendTo($('#result'));
                $('<br><strong> creationDate: </strong>' + todo.creationDate + '<br>').appendTo($('#result'));
				$('<br><strong> difficulty: </strong>' + file.difficulty + '<br>').appendTo($('#result'));
				$('<br><strong> ingredients: </strong>' + todo.ingredients + '<br>').appendTo($('#result'));
				$('<br><strong> preparation: </strong>' + todo.preparation + '<br>').appendTo($('#result'));
				$('<br><strong> title: </strong>' + todo.title + '<br>').appendTo($('#result'));
		
	}).fail(function() {
		$("#result").text("No data to show.");
	});

}

$("#button_to_create").click(function(e) {
	e.preventDefault();
    var newTodo = new Object();
	newTodo.title=$("#title").val();
	newTodo.author=$("#author").val();
	newTodo.difficulty=$("#difficulty").val();
	newTodo.ingredients=$("#ingredients1").val();
	newTodo.steps=$("#steps").val();	

	createTodo(newTodo);
});

function createTodo(newTodo) {
	var url = API_BASE_URL;
	var newtodo = JSON.stringify(todo);

	$("#create_result").text('');

	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		contentType : 'application/json',
		data : newtodo,
	}).done(function(data, status, jqxhr) {
		$('<div class="alert alert-success"> <strong>Ok!</strong> Recipe Created</div>').appendTo($("#result"));
	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Error</strong> Error </div>').appendTo($("#result"));
	});
}

$("#button_edit_todo").click(function(e) {
	e.preventDefault();

    var newTodo = new Object();
	newTodo.title=$("#title").val();
	newTodo.author=$("#author").val();
	newTodo.difficulty=$("#difficulty").val();
	newTodo.ingredients=$("#ingredients1").val();
	newTodo.steps=$("#steps").val();

	updateTodo(newTodo);
});

function updateTodo(newTodo) {
	var url = API_BASE_URL + '/' + title;
	var data = JSON.stringify(Todo);

	$.ajax({
		url : url,
		type : 'PUT',
		crossDomain : true,
		dataType : 'json',
		contentType : 'application/json',
		data : data,
	}).done(function(data, status, jqxhr) {
		$('<div class="alert alert-success"> <strong>Ok!</strong> Recipe Updated</div>').appendTo($("#result"));
	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Error </div>').appendTo($("#result"));
	});

}

$("#button_delete_todo").click(function(e) {
	e.preventDefault();
	deleteTodo($("#title").val());
});

function deleteTodo(title) {
	var url = API_BASE_URL + '/' + title;
	$("#get_repo_result").text('');

	$.ajax({
		url : url,
		type : 'DELETE',
		crossDomain : true,
		dataType : 'json',
		statusCode: {
    		202: function() {$('<div class="alert alert-danger"> <strong>Ok!</strong> File deleted successfully </div>').appendTo($("#result"));}
    	}
	}).done(function(data, status, jqxhr) {
		$('<div class="alert alert-success"> <strong>Ok!</strong> Todo deleted successfully</div>').appendTo($("#result"));	
	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Error </div>').appendTo($("#result"));
	});

}
