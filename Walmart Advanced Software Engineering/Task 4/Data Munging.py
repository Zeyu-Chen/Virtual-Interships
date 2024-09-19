import sqlite3
import pandas as pd

# Connect to the SQLite database
conn = sqlite3.connect('walmart_shipping.db')
cursor = conn.cursor()


def insert_shipment(origin, destination, date):
    """
    Insert a new shipment record into the Shipment table.

    Args:
        origin (int): The ID of the origin location.
        destination (int): The ID of the destination location.
        date (str): The date of the shipment.

    Returns:
        int: The ID of the newly inserted shipment.
    """
    cursor.execute('''
        INSERT INTO Shipment (origin_location_id, destination_location_id, date)
        VALUES (?, ?, ?)
    ''', (origin, destination, date))
    return cursor.lastrowid


def insert_shipment_product(shipment_id, product_id, quantity):
    """
    Insert a new shipment-product association into the Shipment_Product table.

    Args:
        shipment_id (int): The ID of the shipment.
        product_id (int): The ID of the product.
        quantity (int): The quantity of the product in the shipment.
    """
    cursor.execute('''
        INSERT INTO Shipment_Product (shipment_id, product_id, quantity)
        VALUES (?, ?, ?)
    ''', (shipment_id, product_id, quantity))


def get_or_create_product(name, manufacturer):
    """
    Get the ID of an existing product or create a new one if it doesn't exist.

    Args:
        name (str): The name of the product.
        manufacturer (int): The ID of the manufacturer.

    Returns:
        int: The ID of the existing or newly created product.
    """
    cursor.execute('SELECT id FROM Product WHERE name = ? AND manufacturer_id = ?', (name, manufacturer))
    result = cursor.fetchone()
    if result:
        return result[0]
    else:
        cursor.execute('INSERT INTO Product (name, manufacturer_id) VALUES (?, ?)', (name, manufacturer))
        return cursor.lastrowid


def process_spreadsheet_0():
    """Process data from spreadsheet0.csv and insert it into the database."""
    df0 = pd.read_csv('spreadsheet0.csv')
    for _, row in df0.iterrows():
        shipment_id = insert_shipment(row['origin'], row['destination'], row['date'])
        product_id = get_or_create_product(row['product'], row['manufacturer'])
        insert_shipment_product(shipment_id, product_id, row['quantity'])


def process_spreadsheets_1_and_2():
    """Process data from spreadsheet1.csv and spreadsheet2.csv and insert it into the database."""
    df1 = pd.read_csv('spreadsheet1.csv')
    df2 = pd.read_csv('spreadsheet2.csv')

    # Merge spreadsheet 1 and 2 on shipping_identifier
    merged_df = pd.merge(df1, df2, on='shipping_identifier')

    # Group by shipping_identifier and process each shipment
    for shipping_identifier, group in merged_df.groupby('shipping_identifier'):
        origin = group['origin'].iloc[0]
        destination = group['destination'].iloc[0]
        date = group['date'].iloc[0]
        
        shipment_id = insert_shipment(origin, destination, date)
        
        for _, row in group.iterrows():
            product_id = get_or_create_product(row['product'], row['manufacturer'])
            insert_shipment_product(shipment_id, product_id, row['quantity'])


def main():
    """Main function to process all spreadsheets and populate the database."""
    process_spreadsheet_0()
    process_spreadsheets_1_and_2()

    # Commit changes and close the connection
    conn.commit()
    conn.close()

    print("Data insertion completed successfully.")


if __name__ == "__main__":
    main()