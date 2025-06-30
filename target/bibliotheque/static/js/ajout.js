function updateDisabledOptions() {
    let selects = document.querySelectorAll('.categorie-select');
    let selectedValues = Array.from(selects).map(s => s.value);

    selects.forEach(select => {
        let currentValue = select.value;
        Array.from(select.options).forEach(option => {
            option.disabled = selectedValues.includes(option.value) && option.value !== currentValue;
        });
    });
}

function addCategorieSelect() {
    let container = document.getElementById("categories-container");
    let firstSelect = container.querySelector(".categorie-select");

    let newSelectDiv = document.createElement("div");
    newSelectDiv.classList.add("select-group");

    newSelectDiv.innerHTML = firstSelect.parentElement.innerHTML;

    let newSelect = newSelectDiv.querySelector("select");
    newSelect.value = "";

    container.appendChild(newSelectDiv);
    updateDisabledOptions();
}

function removeCategorieSelect(button) {
    let container = document.getElementById("categories-container");
    let groups = container.querySelectorAll(".select-group");

    if (groups.length > 1) {
        button.parentElement.remove();
        updateDisabledOptions();
    } else {
        alert("Au moins une catégorie est requise.");
    }
}

window.onload = function() {
    updateDisabledOptions();
};