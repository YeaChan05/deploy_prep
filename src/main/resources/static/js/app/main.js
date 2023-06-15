var main = {
    init: function() {
        var _this = this;
        $('#btn-save').on('click', function() {
            _this.save();
        });
    },
    save: function() {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/posts',
            dataType: 'text',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            location.reload();
        }).fail(function(error) {
            alert(error);
        });
    }
};

function deletePosts(postId) {
    $.ajax({
        type: 'DELETE',
        url: '/posts/' + postId,
        success: function(response) {
            $("#post-" + postId).remove();
            location.reload();
        },
        error: function(xhr, status, error) {
            console.error(xhr.responseText);
            // 에러 처리 로직 추가
        }
    });
}

function updatePosts(postId) {
    var data = {
        id: postId,
        title: $('#update-title').val(),
        author: $('#update-author').val(),
        content: $('#update-content').val()
    };

    $.ajax({
        type: 'POST',
        url: '/posts/adjust',
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        success: function() {
            alert('글이 수정되었습니다.');
            location.reload();
        },
        error: function(xhr, status, error) {
            alert(error);
        }
    });
}


function openUpdateModal(id, title, author, content) {
    $('#updatePostsModal #update-title').val(title);
    $('#updatePostsModal #update-author').val(author);
    $('#updatePostsModal #update-content').val(content);

    $('#btn-update').attr('onclick', 'updatePosts(' + id + ')');

    $('#updatePostsModal').modal('show');
}


main.init();
