int add_sensor(Sensor *s, Sensor *array, int size)
{
	array = (Sensor*) realloc(array, sizeof(sensor)*size);
	array[size-1] = *s;
	return size++;
}

int remove_sensor(Sensor *array, int size)
{
	array = (Sensor*) realloc(array, sizeof(sensor)*size);
	return size--;
}

void alt_freq(long new_freq, Sensor *s)
{
	*s.frequency = new_freq;
	*s.readings_size = 86400 / new_freq;
}