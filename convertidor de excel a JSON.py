import pandas as pd


df = pd.read_excel('C:/Users/sebas/Downloads/BD Libros.xlsx')


columnas_necesarias = ['nombreLibro', 'autor', 'editor', 'edicion', 'fechaIngreso', 'isbn', 'nombreCategoria', 'nombreSubcategoria', 'sinopsis']
df = df[columnas_necesarias]


for columna in df.columns:
    df[columna] = df[columna].apply(lambda x: x.encode('latin1', errors='ignore').decode('latin1', errors='ignore') if isinstance(x, str) else x)


df = df.drop_duplicates(subset='nombreLibro')

df.to_json('C:/Users/sebas/Downloads/libros.json', orient='records', lines=True, force_ascii=False)
