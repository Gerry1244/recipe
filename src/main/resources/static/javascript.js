const baseurl = 'http://localhost:12121/recipes';

function fillInDetails(data) {
	let html = `
		<h1>${data.id}</h1>
		<h2>Title: ${data.title}</h2>
		<div>Description: ${data.description}</div>
		 
	`;

	for (let instruction of data.instructions) {
		html += `
		<form class="delete-instruction-form" method="post" action="/recipes/${data.id}/instructions/${instruction.id}">
			<div>
				<div>${instruction.description}</div>
				<button>Delete Instruction</button>
			</div>
		</form>
		`;

	}	
	html += `
		<form id="create-instruction-form" method="post" action="/recipes/${data.id}/instructions">   

			<input required id="instruction-description" name="description" instruction=" ">
			<br>
			<button>Add this instruction</button>
		</form>
	`;
	
	
	for (let ingredient of data.ingredients) {
		html += `
		<form class="delete-ingredient-form" method="post" action="/recipes/${data.id}/ingredients/${ingredient.id}">
			<div>
			<div>${ingredient.foodName}</div>
			<button>Delete Ingredient</button> 
			</div>
		</form>
		`;
	
	}	
	html += `
		<form id="create-ingredient-form" method="post" action="/recipes/${data.id}/ingredients">   

			<button>Add this ingredient</button>
			<br>
		<input required id="ingredient-foodName" name="foodName" placeholder="food">
			<br>
		
		<input type="number" required id="ingredient-quantity" name="quantity" placeholder="quantity">
			<br>
		
		<input required id="ingredient-units" name="units" placeholder="unit">
		</form>
	`;
	
	
	console.log(data.id);
	
	$('#recipe-detail').html(html);

}

function createListElement(recipe)  {
	$('<li></li>')
	.html(`
		<a href= "#" data-recipe-id="${recipe.id}">
		${recipe.description}, ${recipe.title}
		</a>
		
		
		<form class="delete-recipe-form" method="post" action="/recipes/${recipe.id}">
		<button>Delete</button>
		</form>
		
	`)
	.appendTo($('#recipe-list'));
}

$(document).on('submit', '.delete-recipe-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, { type: 'DELETE' })
		.done(() => {
			$(this).remove();
			
		})
		.fail(error => console.error(error));
		
});

$(document).on('submit', '.delete-instruction-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, { type: 'DELETE' })
		.done(() => {
			$(this).remove();
			
		})
		.fail(error => console.error(error));
		
});

$(document).on('submit', '.delete-ingredient-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, { type: 'DELETE' })
		.done(() => {
			$(this).remove();
			
		})
		.fail(error => console.error(error));
		
});

$('#create-recipe-form').on('submit', function (e) {
	e.preventDefault();
	
	let payload = {  // New Object
			title: $('#title').val(),
			description: $('#description').val()	
	};
	
	let ajaxOptions = {
		type: 'POST',
		data: JSON.stringify(payload),
		contentType: 'application/json'
			
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (recipe) {
			createListElement(recipe);
		
		})
		.fail(error => console.error(error));  
												
});

$(document).on('submit', '#create-instruction-form', function (e) {  // instruction
	e.preventDefault();
	
	let payload = {
		description: $('#instruction-description').val()
			
	};
	
	let ajaxOptions = {
		type: 'POST',
		data: JSON.stringify(payload),
		contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (data) {
		fillInDetails(data);	
		})
		.fail(error => console.error(error)); 
});

$(document).on('submit', '#create-ingredient-form', function (e) {  // ingredient
	e.preventDefault();
	
	let payload = {
		foodName: $('#ingredient-foodName').val(),
		quantity: $('#ingredient-quantity').val(),
		units: $('#ingredient-units').val()
			
	};
	
	let ajaxOptions = {
		type: 'POST',
		data: JSON.stringify(payload),
		contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (data) {
		fillInDetails(recipe);	
		})
		.fail(error => console.error(error)); 
});

$(document).on('click', 'a[data-recipe-id]', function (e) {
	e.preventDefault();
	const recipeId= $(this).data('recipeId');
	
	
	
	$.getJSON(baseurl + '/' + recipeId, function (data) {
		data.company = data.company || '<i>no company specified</i>'; 
		fillInDetails(data);
	});
	
});

$.getJSON(baseurl, function(data) {
	console.log('I got some data:', data);
	if (data.length) { 
		for (let recipe of data)  {
			createListElement(recipe);
				
		}
		
	} else {
		$('<li></li>')
		.css('color', 'red')
			.html('You have no data.')
			.appendTo($('#recipe-list'));
			
	}
});

