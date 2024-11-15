import pandas as pd


df = pd.read_excel('ruta\\BD Libros.xlsx')


for columna in df.columns:
    df[columna] = df[columna].apply(lambda x: x.encode('latin1', errors='ignore').decode('latin1', errors='ignore') if isinstance(x, str) else x)


df.to_json('ruta\\libros.json', orient='records', lines=True, force_ascii=False)