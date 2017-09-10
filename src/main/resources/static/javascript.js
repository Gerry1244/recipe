const baseurl = 'http://localhost:12121/recipes';

$(document).on('click', 'a[recipe-id]', function (e) {
	e.preventDefault();
	const recipeId= $(this).data('recipeId');
	
	$.getJSON(baseurl + '/' + recipeId, function (data) {
		data.company = data.company || '<i>no company specified</i>';  //default operator pattern
		$('#recipe-detail')
			.html(`
					<h1>${data.recipeId} ${data.name}</h1>
					<h2>${data.description}</h2>
					<div>Title: ${data.description}</div>
			`);
			
		
	});
	
});

$.getJSON(baseurl, function(data) {
	console.log('I got some data:', data);
	if (data.length) { 
		for (let recipe of data)  {
			$('<li></li>')
				.html('<a href= "#" recipe-id=" ' + recipe.id + '">' + recipe.numberMinutesin + ', ' + recipe.title + '</a>)')
				.appendTo($('#recipe-list'));
				
		}
		
	} else {
		$('<li></li>')
		.css('color', 'red')
			.html('You have no data.')
			.appendTo($('#recipe-list'));
			
	}
});

