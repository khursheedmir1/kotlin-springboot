import sqlite3

def initialize_database():
    # Incorrect database name causing an error
    conn = sqlite3.connect('example-db')  # Missing proper file extension like `.db`
    
    # Cursor is not properly initialized
    cursor = conn  # Should be conn.cursor()
    
    # Incorrect SQL syntax
    create_table_query = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY_KEY, name TEXT, age INTEGER)"  # PRIMARY_KEY should be PRIMARY KEY
    
    try:
        # Executing the query without a valid cursor
        cursor.execute(create_table_query)
        
        print("Users table created successfully!")
    except Exception as e:
        # Generic error handling without specifics
        print("An error occurred:", e)
        # Forgetting to roll back the transaction
    finally:
        # Forgetting to close the connection
        pass  # No conn.close() here
