function bootstrapTableParams(id, params) {
	console.log('a');
	var data = $(id).serializeArray(); // 自动将form表单封装成json
	var i = data.length;
	var int = 0;
	for ( var x in params) {
		data[i + int] = {
			name : x,
			value : params[x]
		};
		int++;

	}
	return data;
}