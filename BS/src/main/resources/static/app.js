// app.js
document.getElementById('contactForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const address = document.getElementById('address').value;
    const phone = document.getElementById('phone').value;

    fetch('/api/contacts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, address, phone }),
    }).then(response => response.json())
        .then(data => {
            loadContacts();
        });
});

function loadContacts() {
    fetch('/api/contacts')
        .then(response => response.json())
        .then(data => {
            const contactList = document.getElementById('contactList');
            contactList.innerHTML = '';
            data.forEach(contact => {
                const li = document.createElement('li');
                li.textContent = `Name: ${contact.name}, Address: ${contact.address}, Phone: ${contact.phone}`;
                contactList.appendChild(li);
            });
        });
}

loadContacts();
