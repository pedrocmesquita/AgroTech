int add_sensor(sensor s, sensor* array, int size)
{
array = realloc(array, sizeof(sensor)*size);
array[size-1]=s;
}

int remove_sensor(sensor s, sensor* array, int size)
{
array = realloc(array, sizeof(sensor)*size);
}