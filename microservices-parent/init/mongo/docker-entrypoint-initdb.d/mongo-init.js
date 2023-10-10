// Log the start if the script executes
print('START');

// Switch to the 'product-service' database or create it if it doesn't exist
db = db.getSiblingDB('product-service');

// Create a user for the 'product-service' database
db.createUser({
    user: 'casey',
    pwd: 'pass',
    roles: [{ role: 'readWrite', db: 'product-service'}],
})

// Create a new collection named 'user' within the product-service database
db.createCollection('user');

print('END');