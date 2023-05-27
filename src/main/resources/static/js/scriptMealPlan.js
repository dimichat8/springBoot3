
document.addEventListener('DOMContentLoaded', function () {
    var openButtonForBreakfast = document.getElementById('openButtonForBreakfast');
    var panel = document.getElementById('panel');

    openButtonForBreakfast.addEventListener('click', function () {
            if (panel.style.display === 'block') {
                panel.style.display = 'none';
            } else {
                panel.style.display = 'block';
            }

        });
});

document.addEventListener('DOMContentLoaded', function () {
    var openButtonForDessert = document.getElementById('openButtonForDessert');
    var panel = document.getElementById('panel2');

    openButtonForDessert.addEventListener('click', function () {
        if (panel.style.display === 'block') {
            panel.style.display = 'none';
        } else {
            panel.style.display = 'block';
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var openButtonForLunch = document.getElementById('openButtonForLunch');
    var panel = document.getElementById('panel3');

    openButtonForLunch.addEventListener('click', function () {
        if (panel.style.display === 'block') {
            panel.style.display = 'none';
        } else {
            panel.style.display = 'block';
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var openButtonForSnack = document.getElementById('openButtonForSnack');
    var panel = document.getElementById('panel4');

    openButtonForSnack.addEventListener('click', function () {
        if (panel.style.display === 'block') {
            panel.style.display = 'none';
        } else {
            panel.style.display = 'block';
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var openButtonForDinner = document.getElementById('openButtonForDinner');
    var panel = document.getElementById('panel5');

    openButtonForDinner.addEventListener('click', function () {
        if (panel.style.display === 'block') {
            panel.style.display = 'none';
        } else {
            panel.style.display = 'block';
        }
    });
});
document.addEventListener('DOMContentLoaded', function toggleButtons (MealId) {
    var previousButtonsContainer = null;

    var buttonsContainer = document.getElementById('buttons-' + MealId);

    if (previousButtonsContainer !== null && previousButtonsContainer !== buttonsContainer) {
        previousButtonsContainer.classList.add('hidden');
    }

    buttonsContainer.classList.toggle('hidden');
    previousButtonsContainer = buttonsContainer;

});



