function handleInputEvents(input) {
    var label = input.previousElementSibling;

    input.addEventListener('keyup', function() {
        if (input.value === '') {
            label.classList.remove('active', 'highlight');
        } else {
            label.classList.add('active', 'highlight');
        }
    });

    input.addEventListener('blur', function() {
        if (input.value === '') {
            label.classList.remove('active', 'highlight');
        } else {
            label.classList.remove('highlight');
        }
    });

    input.addEventListener('focus', function() {
        if (input.value === '') {
            label.classList.remove('highlight');
        } else {
            label.classList.add('highlight');
        }
    });
}

var form = document.querySelector('.form');
var inputs = form.querySelectorAll('input, textarea');

inputs.forEach(function(input) {
    handleInputEvents(input);
});
document.addEventListener('DOMContentLoaded', function() {
    function handleTabClick(tab) {
        tab.addEventListener('click', function(e) {
            e.preventDefault();

            var tabs = document.querySelectorAll('.tab');
            tabs.forEach(function(t) {
                t.classList.remove('active');
            });

            var tabContents = document.querySelectorAll('.tab-content > div');
            tabContents.forEach(function(content) {
                content.style.display = 'none';
            });

            tab.parentElement.classList.add('active');
            var target = tab.getAttribute('href');
            var selectedContent = document.querySelector(target);
            selectedContent.style.display = 'block';
        });
    }

    var tabs = document.querySelectorAll('.tab a');
    tabs.forEach(function(tab) {
        handleTabClick(tab);
    });
});
